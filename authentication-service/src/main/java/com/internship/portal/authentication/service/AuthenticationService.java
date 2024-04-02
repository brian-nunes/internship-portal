package com.internship.portal.authentication.service;

import com.internship.portal.auth.commons.service.AuthService;
import com.internship.portal.authentication.dto.LoginDTO;
import com.internship.portal.authentication.dto.LoginResponseDTO;
import com.internship.portal.authentication.dto.UserSignUpDTO;
import com.internship.portal.authentication.exception.BaseBusinessException;
import com.internship.portal.authentication.model.User;
import com.internship.portal.authentication.repository.UserRepository;
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
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthService authService;

    public LoginResponseDTO login(LoginDTO loginDTO){
        User user = userRepository.findByDocumentNumber(loginDTO.getDocumentNumber()).orElse(null);
        if (user == null) {
            throw new BaseBusinessException("ERROR_AUTHENTICATION_0001", "Usuário não encontrado", HttpStatus.FORBIDDEN);
        }
        log.info("User [{}] found: [{}]", loginDTO.getDocumentNumber(), user);
        if (!passwordEncoder.matches(loginDTO.getPassword(), user.getPassword())) {
            throw new BaseBusinessException("ERROR_AUTHENTICATION_0002", "Credenciais inválidas", HttpStatus.FORBIDDEN);
        }
        log.info("Password checked!");
        String sessionHash = authService.generateSession(user.getUsername(), user.getDocumentNumber(), user.getMail());
        String accessToken = authService.generateToken(sessionHash);
        String sessionData = authService.generateCompressedSessionData(sessionHash);
        return LoginResponseDTO.builder().accessToken(accessToken).sessionData(sessionData).build();
    }

    public User signup(UserSignUpDTO userSignUpDTO){
        String hashedPassword = passwordEncoder.encode(userSignUpDTO.getPassword());
        userSignUpDTO.setPassword(hashedPassword);
        User user = new User();
        user.setPassword(userSignUpDTO.getPassword());
        user.setMail(userSignUpDTO.getMail());
        user.setUsername(userSignUpDTO.getUsername());
        log.info("Saving user: {} | hashed: {}", user.getUsername(), hashedPassword);
        userRepository.save(user);
        return user;
    }

    public UserSignUpDTO getUserInfo(String documentNumber){
        User user = userRepository.findByDocumentNumber(documentNumber).orElseThrow(() -> new BaseBusinessException("ERROR_AUTHENTICATION_0001", "Usuário não encontrado", HttpStatus.FORBIDDEN));
        return UserSignUpDTO.builder().mail(user.getMail()).documentNumber(user.getDocumentNumber()).username(user.getUsername()).build();
    }

    public void logout(String accessToken) {
        authService.deleteSession(accessToken);
    }
}
