package com.internship.portal.physical.activity.controller;

import com.internship.portal.physical.activity.service.PhysicalActivityService;
import com.internship.portal.microservices.commons.model.Session;
import com.internship.portal.microservices.commons.service.SessionService;
import com.internship.portal.physical.activity.dto.PhysicalActivityDTO;
import com.internship.portal.physical.activity.dto.SuccessDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/physical-activity")
public class PhysicalActivityController {

    @Autowired
    private PhysicalActivityService physicalActivityService;

    @Autowired
    private SessionService sessionService;

    @PostMapping
    public ResponseEntity<SuccessDTO> postPhysicalActivity( @RequestBody PhysicalActivityDTO physicalActivityDTO) {
        physicalActivityService.postPhysicalActivity(physicalActivityDTO);
        return new ResponseEntity<>(SuccessDTO.builder().success(true).build(), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<SuccessDTO> putPhysicalActivity(@RequestBody PhysicalActivityDTO physicalActivityDTO) {
        physicalActivityService.putPhysicalActivity(physicalActivityDTO);
        return new ResponseEntity<>(SuccessDTO.builder().success(true).build(), HttpStatus.OK);
    }

    @GetMapping("/{userDocument}")
    public ResponseEntity<PhysicalActivityDTO> getPhysicalActivityByProfessional(@PathVariable("userDocument") String userDocument) {
        PhysicalActivityDTO physicalActivityDTO = physicalActivityService.getPhysicalActivity(userDocument);
        return new ResponseEntity<>(physicalActivityDTO, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<PhysicalActivityDTO> getPhysicalActivityByUser(@RequestHeader("SessionData") String sessionData) {
        Session session = sessionService.decodeSessionData(sessionData);
        PhysicalActivityDTO physicalActivityDTO = physicalActivityService.getPhysicalActivity(session.getDocumentNumber());
        return new ResponseEntity<>(physicalActivityDTO, HttpStatus.OK);
    }
}