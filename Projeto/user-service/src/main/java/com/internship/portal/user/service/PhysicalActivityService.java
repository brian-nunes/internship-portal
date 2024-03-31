package com.internship.portal.user.service;

import com.internship.portal.microservices.commons.model.Session;
import com.internship.portal.user.dto.EmotionDTO;
import com.internship.portal.microservices.commons.exception.BaseBusinessException;
import com.internship.portal.microservices.commons.service.SessionService;
import com.internship.portal.user.model.Emotion;
import com.internship.portal.user.repository.EmotionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class EmotionService {
    @Autowired
    private EmotionRepository emotionRepository;

    public Emotion postEmotion(EmotionDTO emotionDTO){
        return emotionRepository.save(new Emotion(emotionDTO));
    }

    public Emotion putEmotion(EmotionDTO emotionDTO){
        Emotion emotion = emotionRepository.findById(emotionDTO.getId()).orElseThrow(() -> new BaseBusinessException("EMOTION_NOT_FOUND", "Emocao nao encontrada", HttpStatus.FORBIDDEN));
        emotion.setStatus(emotionDTO.getStatus());
        emotion.setDhRegistro(emotionDTO.getDhRegistro());
        emotion.setObservacao(emotionDTO.getObservacao());
        return emotionRepository.save(emotion);
    }

    public EmotionDTO getEmotion(Long emotionId){
        return new EmotionDTO(emotionRepository.findById(emotionId).orElseThrow(() -> new BaseBusinessException("EMOTION_NOT_FOUND", "Emocao nao encontrada", HttpStatus.FORBIDDEN)));
    }

    public List<EmotionDTO> getEmotions(String fromString, String toString, String userDocument){
        List<Emotion> emotions = emotionRepository.findAllDhRegistroBetween(stringToDate(fromString), stringToDate(toString), userDocument);
        List<EmotionDTO> emotionDTOS = new ArrayList<>();
        emotions.forEach(emotion -> emotionDTOS.add(new EmotionDTO(emotion)));
        return emotionDTOS;
    }

    private Date stringToDate(String stringDate){
        try {
            String pattern = "dd-MM-yyyy";
            SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
            return dateFormat.parse(stringDate);
        } catch (ParseException e) {
            throw new BaseBusinessException("DATE_FORMAT_EXCEPTION", "Incorrect date format, please use dd-MM-yyyy", HttpStatus.BAD_REQUEST);
        }
    }
}
