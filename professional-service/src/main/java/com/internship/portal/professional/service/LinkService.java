package com.internship.portal.professional.service;

import com.internship.portal.microservices.commons.exception.BaseBusinessException;
import com.internship.portal.professional.dto.TokenDTO;
import com.internship.portal.professional.model.Link;
import com.internship.portal.professional.model.Token;
import com.internship.portal.professional.repository.LinkRepository;
import com.internship.portal.professional.repository.TokenRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@Slf4j
public class LinkService {

    @Autowired
    private TokenRepository tokenRepository;

    @Autowired
    private LinkRepository linkRepository;

    private static final String CAPITAL_LETTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String DIGITS = "0123456789";
    private static final int TOKEN_LENGTH = 6;

    private static final Random random = new Random();

    public TokenDTO newToken(String professionalDocument){
        String tokenCode = generateToken();
        while(tokenRepository.existsByToken(tokenCode)){
            tokenCode = generateToken();
        }
        Token token = new Token(tokenCode, professionalDocument);
        tokenRepository.save(token);
        return new TokenDTO(token);
    }

    public boolean newLink(String userDocument, String tokenCode){
        Token token = tokenRepository.findByTokenIgnoreCase(tokenCode).orElseThrow(() -> new BaseBusinessException("TOKEN_NOT_FOUND", "Given code doenst match any valid token", HttpStatus.FORBIDDEN));
        Link link = new Link(token.getIdProfessional(), userDocument);
        linkRepository.save(link);
        tokenRepository.delete(token);
        return true;
    }

    public boolean deleteLink(String userDocument, String professionalDocument){
        Link link = linkRepository.findByIdProfessionalAndIdUser(professionalDocument, userDocument).orElseThrow(() -> new BaseBusinessException("LINK_NOT_FOUND", "User and professional arent linked", HttpStatus.FORBIDDEN));
        linkRepository.delete(link);
        return true;
    }

    private String generateToken(){
        StringBuilder tokenBuilder = new StringBuilder();
        for (int i = 0; i < TOKEN_LENGTH; i++) {
            String source = i % 2 == 0 ? CAPITAL_LETTERS : DIGITS;
            int index = random.nextInt(source.length());
            tokenBuilder.append(source.charAt(index));
        }
        return tokenBuilder.toString();
    }


}
