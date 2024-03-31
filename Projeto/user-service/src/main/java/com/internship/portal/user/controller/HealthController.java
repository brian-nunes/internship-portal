package com.internship.portal.user.controller;

import com.internship.portal.microservices.commons.model.Session;
import com.internship.portal.microservices.commons.service.SessionService;
import com.internship.portal.user.dto.PhysicalActivityDTO;
import com.internship.portal.user.dto.SuccessDTO;
import com.internship.portal.user.service.PhysicalActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/physical-activity")
public class PhysicalActivityController {

    @Autowired
    private PhysicalActivityService physicalActivityService;

    @Autowired
    private SessionService sessionService;

    @PostMapping
    public ResponseEntity<SuccessDTO> postPhysicalActivity(@RequestBody PhysicalActivityDTO physicalActivityDTO) {
        physicalActivityService.postPhysicalActivity(physicalActivityDTO);
        return new ResponseEntity<>(SuccessDTO.builder().success(true).build(), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<PhysicalActivityDTO>> getPhysicalActivity(@RequestHeader("SessionData") String sessionDataHeader, @RequestParam(value = "from", required = false, defaultValue = "01-01-1970") String from, @RequestParam(value = "to", required = false, defaultValue = "31-12-2100") String to) {
        Session session = sessionService.decodeSessionData(sessionDataHeader);
        List<PhysicalActivityDTO> physicalActivitys = physicalActivityService.getPhysicalActivitys(from, to, session.getDocumentNumber());
        return new ResponseEntity<>(physicalActivitys, HttpStatus.OK);
    }

    @GetMapping("/{physicalActivityId}")
    public ResponseEntity<PhysicalActivityDTO> getPhysicalActivity(@PathVariable("physicalActivityId") Long physicalActivityId) {
        PhysicalActivityDTO physicalActivityDTO = physicalActivityService.getPhysicalActivity(physicalActivityId);
        return new ResponseEntity<>(physicalActivityDTO, HttpStatus.OK);
    }

    @PutMapping("/{physicalActivityId}")
    public ResponseEntity<SuccessDTO> putPhysicalActivity(@RequestBody PhysicalActivityDTO physicalActivityDTO) {
        physicalActivityService.putPhysicalActivity(physicalActivityDTO);
        return new ResponseEntity<>(SuccessDTO.builder().success(true).build(), HttpStatus.OK);
    }
}