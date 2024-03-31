package com.internship.portal.user.controller;

import com.internship.portal.microservices.commons.model.Session;
import com.internship.portal.microservices.commons.service.SessionService;
import com.internship.portal.user.dto.NutritionDTO;
import com.internship.portal.user.dto.SuccessDTO;
import com.internship.portal.user.service.NutritionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/nutrition")
public class NutritionController {

    @Autowired
    private NutritionService nutritionService;

    @Autowired
    private SessionService sessionService;

    @PostMapping
    public ResponseEntity<SuccessDTO> postNutrition(@RequestBody NutritionDTO nutritionDTO) {
        nutritionService.postNutrition(nutritionDTO);
        return new ResponseEntity<>(SuccessDTO.builder().success(true).build(), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<NutritionDTO>> getNutrition(@RequestHeader("SessionData") String sessionDataHeader, @RequestParam(value = "from", required = false, defaultValue = "01-01-1970") String from, @RequestParam(value = "to", required = false, defaultValue = "31-12-2100") String to) {
        Session session = sessionService.decodeSessionData(sessionDataHeader);
        List<NutritionDTO> nutritions = nutritionService.getNutritions(from, to, session.getDocumentNumber());
        return new ResponseEntity<>(nutritions, HttpStatus.OK);
    }

    @GetMapping("/{nutritionId}")
    public ResponseEntity<NutritionDTO> getNutrition(@PathVariable("nutritionId") Long nutritionId) {
        NutritionDTO nutritionDTO = nutritionService.getNutrition(nutritionId);
        return new ResponseEntity<>(nutritionDTO, HttpStatus.OK);
    }

    @PutMapping("/{nutritionId}")
    public ResponseEntity<SuccessDTO> putNutrition(@RequestBody NutritionDTO nutritionDTO) {
        nutritionService.putNutrition(nutritionDTO);
        return new ResponseEntity<>(SuccessDTO.builder().success(true).build(), HttpStatus.OK);
    }
}