package com.internship.portal.user.controller;

import com.internship.portal.microservices.commons.model.Session;
import com.internship.portal.microservices.commons.service.SessionService;
import com.internship.portal.user.dto.SuccessDTO;
import com.internship.portal.user.dto.EmotionDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.internship.portal.user.service.EmotionService;

import java.util.List;

@RestController
@RequestMapping("/emotion")
public class EmotionController {

    @Autowired
    private EmotionService emotionService;

    @Autowired
    private SessionService sessionService;

    @PostMapping
    public ResponseEntity<SuccessDTO> postEmotion(@RequestBody EmotionDTO emotionDTO) {
        emotionService.postEmotion(emotionDTO);
        return new ResponseEntity<>(SuccessDTO.builder().success(true).build(), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<EmotionDTO>> getEmotion(@RequestHeader("SessionData") String sessionDataHeader, @RequestParam(value = "from", required = false, defaultValue = "01-01-1970") String from, @RequestParam(value = "to", required = false, defaultValue = "31-12-2100") String to) {
        Session session = sessionService.decodeSessionData(sessionDataHeader);
        List<EmotionDTO> emotions = emotionService.getEmotions(from, to, session.getDocumentNumber());
        return new ResponseEntity<>(emotions, HttpStatus.OK);
    }

    @GetMapping("/{emotionId}")
    public ResponseEntity<EmotionDTO> getEmotion(@PathVariable("emotionId") Long emotionId) {
        EmotionDTO emotionDTO = emotionService.getEmotion(emotionId);
        return new ResponseEntity<>(emotionDTO, HttpStatus.OK);
    }

    @PutMapping("/{emotionId}")
    public ResponseEntity<SuccessDTO> putEmotion(@RequestBody EmotionDTO emotionDTO) {
        emotionService.putEmotion(emotionDTO);
        return new ResponseEntity<>(SuccessDTO.builder().success(true).build(), HttpStatus.OK);
    }
}