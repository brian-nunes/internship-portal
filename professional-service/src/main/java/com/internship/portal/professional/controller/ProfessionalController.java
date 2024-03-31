package com.internship.portal.professional.controller;

import com.internship.portal.microservices.commons.service.SessionService;
import com.internship.portal.professional.dto.SuccessDTO;
import com.internship.portal.professional.dto.NoteDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.internship.portal.professional.service.ProfessionalService;

@RestController
@RequestMapping("/note")
public class ProfessionalController {

    @Autowired
    private ProfessionalService professionalService;

    @Autowired
    private SessionService sessionService;

    @PostMapping
    public ResponseEntity<SuccessDTO> postNote(@RequestHeader("SessionData") String sessionDataHeader, @RequestBody NoteDTO noteDTO) {
        String professionalDocument = sessionService.decodeSessionData(sessionDataHeader).getDocumentNumber();
        professionalService.postNote(noteDTO, professionalDocument);
        return new ResponseEntity<>(SuccessDTO.builder().success(true).build(), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<NoteDTO> getNote(@RequestHeader("SessionData") String sessionDataHeader, @RequestParam(value = "patientDocument") String patientDocument) {
        String professionalDocument = sessionService.decodeSessionData(sessionDataHeader).getDocumentNumber();
        return new ResponseEntity<>(professionalService.getNote(professionalDocument, patientDocument), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<SuccessDTO> putNote(@RequestHeader("SessionData") String sessionDataHeader, @RequestBody NoteDTO noteDTO) {
        String professionalDocument = sessionService.decodeSessionData(sessionDataHeader).getDocumentNumber();
        professionalService.putNote(noteDTO, professionalDocument);
        return new ResponseEntity<>(SuccessDTO.builder().success(true).build(), HttpStatus.OK);
    }
}