package com.internship.portal.user.controller;

import com.internship.portal.microservices.commons.model.Session;
import com.internship.portal.microservices.commons.service.SessionService;
import com.internship.portal.user.dto.HealthDTO;
import com.internship.portal.user.dto.SuccessDTO;
import com.internship.portal.user.service.HealthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/health")
public class HealthController {

    @Autowired
    private HealthService healthService;

    @Autowired
    private SessionService sessionService;

    @PostMapping
    public ResponseEntity<SuccessDTO> postHealth(@RequestBody HealthDTO healthDTO) {
        healthService.postHealth(healthDTO);
        return new ResponseEntity<>(SuccessDTO.builder().success(true).build(), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<HealthDTO>> getHealth(@RequestHeader("SessionData") String sessionDataHeader, @RequestParam(value = "from", required = false, defaultValue = "01-01-1970") String from, @RequestParam(value = "to", required = false, defaultValue = "31-12-2100") String to) {
        Session session = sessionService.decodeSessionData(sessionDataHeader);
        List<HealthDTO> healths = healthService.getHealths(from, to, session.getDocumentNumber());
        return new ResponseEntity<>(healths, HttpStatus.OK);
    }

    @GetMapping("/{healthId}")
    public ResponseEntity<HealthDTO> getHealth(@PathVariable("healthId") Long healthId) {
        HealthDTO healthDTO = healthService.getHealth(healthId);
        return new ResponseEntity<>(healthDTO, HttpStatus.OK);
    }

    @PutMapping("/{healthId}")
    public ResponseEntity<SuccessDTO> putHealth(@RequestBody HealthDTO healthDTO) {
        healthService.putHealth(healthDTO);
        return new ResponseEntity<>(SuccessDTO.builder().success(true).build(), HttpStatus.OK);
    }
}