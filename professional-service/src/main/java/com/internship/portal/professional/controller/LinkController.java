package com.internship.portal.professional.controller;

import com.internship.portal.microservices.commons.service.SessionService;
import com.internship.portal.professional.dto.LinkDTO;
import com.internship.portal.professional.dto.NoteDTO;
import com.internship.portal.professional.dto.SuccessDTO;
import com.internship.portal.professional.dto.TokenDTO;
import com.internship.portal.professional.service.LinkService;
import com.internship.portal.professional.service.ProfessionalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @DeleteMapping("/{linkId}")
    public ResponseEntity<SuccessDTO> deleteLink(@RequestHeader("SessionData") String sessionDataHeader, @PathVariable("linkId") Long linkId) {
        String userDocument = sessionService.decodeSessionData(sessionDataHeader).getDocumentNumber();
        linkService.deleteLink(userDocument, linkId);
        return new ResponseEntity<>(SuccessDTO.builder().success(true).build(), HttpStatus.OK);
    }

    @GetMapping("/user")
    public ResponseEntity<List<LinkDTO>> getUserLinks(@RequestHeader("SessionData") String sessionDataHeader) {
        String userDocument = sessionService.decodeSessionData(sessionDataHeader).getDocumentNumber();
        List<LinkDTO> links = linkService.getUserLinks(userDocument);
        return new ResponseEntity<>(links, HttpStatus.OK);
    }

    @GetMapping("/professional")
    public ResponseEntity<List<LinkDTO>> getProfessionalLinks(@RequestHeader("SessionData") String sessionDataHeader) {
        String userDocument = sessionService.decodeSessionData(sessionDataHeader).getDocumentNumber();
        List<LinkDTO> links = linkService.getProfessionalLinks(userDocument);
        return new ResponseEntity<>(links, HttpStatus.OK);
    }
}