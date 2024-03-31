package com.internship.portal.authentication.controller;

import com.internship.portal.authentication.dto.LoginDTO;
import com.internship.portal.authentication.dto.LoginResponseDTO;
import com.internship.portal.authentication.dto.UserSignUpDTO;
import com.internship.portal.authentication.model.User;
import com.internship.portal.authentication.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginDTO loginDTO) {
        LoginResponseDTO loginResponseDTO = authenticationService.login(loginDTO);
        return ResponseEntity.ok(loginResponseDTO);
    }

    @PostMapping("/logout")
    public ResponseEntity<Boolean> logout(@RequestHeader(HttpHeaders.AUTHORIZATION) String accessToken) {
        authenticationService.logout(accessToken);
        return ResponseEntity.ok(Boolean.TRUE);
    }

    @PostMapping("/signup")
    public ResponseEntity<User> signup(@RequestBody UserSignUpDTO userSignUpDTO) {
        User userSaved = authenticationService.signup(userSignUpDTO);
        return ResponseEntity.ok(userSaved);
    }
}