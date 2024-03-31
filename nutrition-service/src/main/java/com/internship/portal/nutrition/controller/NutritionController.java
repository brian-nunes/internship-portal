package com.internship.portal.nutrition.controller;

import com.internship.portal.microservices.commons.model.Session;
import com.internship.portal.microservices.commons.service.SessionService;
import com.internship.portal.nutrition.dto.NutritionDTO;
import com.internship.portal.nutrition.dto.SuccessDTO;
import com.internship.portal.nutrition.service.NutritionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/nutrition")
public class NutritionController {

    @Autowired
    private NutritionService nutritionService;

    @Autowired
    private SessionService sessionService;

    @PostMapping
    public ResponseEntity<SuccessDTO> postNutrition( @RequestBody NutritionDTO nutritionDTO) {
        nutritionService.postNutrition(nutritionDTO);
        return new ResponseEntity<>(SuccessDTO.builder().success(true).build(), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<SuccessDTO> putNutrition(@RequestBody NutritionDTO nutritionDTO) {
        nutritionService.putNutrition(nutritionDTO);
        return new ResponseEntity<>(SuccessDTO.builder().success(true).build(), HttpStatus.OK);
    }

    @GetMapping("/{userDocument}")
    public ResponseEntity<NutritionDTO> getNutritionByProfessional(@PathVariable("userDocument") String userDocument) {
        NutritionDTO nutritionDTO = nutritionService.getNutrition(userDocument);
        return new ResponseEntity<>(nutritionDTO, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<NutritionDTO> getNutritionByUser(@RequestHeader("SessionData") String sessionData) {
        Session session = sessionService.decodeSessionData(sessionData);
        NutritionDTO nutritionDTO = nutritionService.getNutrition(session.getDocumentNumber());
        return new ResponseEntity<>(nutritionDTO, HttpStatus.OK);
    }
}