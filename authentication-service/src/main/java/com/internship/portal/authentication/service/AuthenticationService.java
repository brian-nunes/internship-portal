package com.internship.portal.authentication.service;

import com.internship.portal.auth.commons.service.AuthService;
import com.internship.portal.authentication.dto.LoginDTO;
import com.internship.portal.authentication.dto.LoginResponseDTO;
import com.internship.portal.authentication.dto.UserSignUpDTO;
import com.internship.portal.authentication.exception.BaseBusinessException;
import com.internship.portal.authentication.model.Role;
import com.internship.portal.authentication.model.User;
import com.internship.portal.authentication.model.UserRole;
import com.internship.portal.authentication.repository.RoleRepository;
import com.internship.portal.authentication.repository.UserRepository;
import com.internship.portal.authentication.repository.UserRoleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AuthenticationService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthService authService;

    public LoginResponseDTO login(LoginDTO loginDTO){
        User user = userRepository.findByUsername(loginDTO.getUsername()).orElse(null);
        if (user == null) {
            throw new BaseBusinessException("ERROR_AUTHENTICATION_0001", "Usuário não encontrado", HttpStatus.FORBIDDEN);
        }
        log.info("User [{}] found: [{}]", loginDTO.getUsername(), user);
        Role role = roleRepository.findByRole(loginDTO.getRole()).orElse(null);
        if (role == null) {
            throw new BaseBusinessException("ERROR_AUTHENTICATION_0003", "Role não encontrada", HttpStatus.FORBIDDEN);
        }
        log.info("Role [{}] found: [{}]", loginDTO.getRole(), role);
        if (!passwordEncoder.matches(loginDTO.getPassword(), user.getPassword())) {
            throw new BaseBusinessException("ERROR_AUTHENTICATION_0002", "Credenciais inválidas", HttpStatus.FORBIDDEN);
        }
        log.info("Password checked!");
        UserRole userRole = userRoleRepository.findByUserAndRole(role, user).orElse(null);
        if (userRole == null) {
            throw new BaseBusinessException("ERROR_AUTHENTICATION_0004", "Usuário não possui essa role", HttpStatus.FORBIDDEN);
        }
        log.info("UserRole found: [{}]", userRole);
        String sessionHash = authService.generateSession(user.getUsername(), user.getDocumentNumber(), user.getMail(), role.getRole());
        String accessToken = authService.generateToken(sessionHash);
        String sessionData = authService.generateCompressedSessionData(sessionHash);
        return LoginResponseDTO.builder().accessToken(accessToken).sessionData(sessionData).build();
    }

    public User signup(UserSignUpDTO userSignUpDTO){
        Role role = roleRepository.findByRole(userSignUpDTO.getRole()).orElse(null);
        if (role == null) {
            throw new BaseBusinessException("ERROR_AUTHENTICATION_0003", "Role não encontrada", HttpStatus.FORBIDDEN);
        }

        String hashedPassword = passwordEncoder.encode(userSignUpDTO.getPassword());
        userSignUpDTO.setPassword(hashedPassword);
        User user = new User();
        user.setPassword(userSignUpDTO.getPassword());
        user.setMail(userSignUpDTO.getMail());
        user.setUsername(userSignUpDTO.getUsername());
        log.info("Saving user: {} | hashed: {}", user.getUsername(), hashedPassword);
        userRepository.save(user);
        UserRole userRole = new UserRole();
        userRole.setUser(user);
        userRole.setRole(role);
        userRoleRepository.save(userRole);
        return user;
    }

    public void logout(String accessToken) {
        authService.deleteSession(accessToken);
    }
}
