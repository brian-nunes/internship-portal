package com.internship.portal.professional.controller;

import com.internship.portal.microservices.commons.service.SessionService;
import com.internship.portal.professional.dto.NoteDTO;
import com.internship.portal.professional.dto.SuccessDTO;
import com.internship.portal.professional.dto.TokenDTO;
import com.internship.portal.professional.service.LinkService;
import com.internship.portal.professional.service.ProfessionalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/link")
public class LinkController {

    @Autowired
    private LinkService linkService;

    @Autowired
    private SessionService sessionService;

    @PostMapping("/new-token")
    public ResponseEntity<TokenDTO> newToken(@RequestHeader("SessionData") String sessionDataHeader) {
        String professionalDocument = sessionService.decodeSessionData(sessionDataHeader).getDocumentNumber();
        TokenDTO tokenDTO = linkService.newToken(professionalDocument);
        return new ResponseEntity<>(tokenDTO, HttpStatus.OK);
    }

    @PostMapping("/{token}")
    public ResponseEntity<SuccessDTO> newLink(@RequestHeader("SessionData") String sessionDataHeader, @PathVariable("token") String token) {
        String userDocument = sessionService.decodeSessionData(sessionDataHeader).getDocumentNumber();
        linkService.newLink(userDocument, token);
        return new ResponseEntity<>(SuccessDTO.builder().success(true).build(), HttpStatus.OK);
    }

    @DeleteMapping("/{professionalId}")
    public ResponseEntity<SuccessDTO> deleteLink(@RequestHeader("SessionData") String sessionDataHeader, @PathVariable("professionalId") String professionalId) {
        String userDocument = sessionService.decodeSessionData(sessionDataHeader).getDocumentNumber();
        linkService.deleteLink(userDocument, professionalId);
        return new ResponseEntity<>(SuccessDTO.builder().success(true).build(), HttpStatus.OK);
    }
}