package com.internship.portal.user.service;

import com.internship.portal.microservices.commons.exception.BaseBusinessException;
import com.internship.portal.user.dto.HealthDTO;
import com.internship.portal.user.model.Health;
import com.internship.portal.user.repository.HealthRepository;
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
public class HealthService {
    @Autowired
    private HealthRepository healthRepository;

    public Health postHealth(HealthDTO healthDTO){
        return healthRepository.save(new Health(healthDTO));
    }

    public Health putHealth(HealthDTO healthDTO){
        Health health = healthRepository.findById(healthDTO.getId()).orElseThrow(() -> new BaseBusinessException("NUTRITION_NOT_FOUND", "Nutricao nao encontrada", HttpStatus.FORBIDDEN));
        health.setWeight(healthDTO.getWeight());
        health.setOxigen(healthDTO.getOxigen());
        health.setBloodPressure(healthDTO.getBloodPressure());
        health.setDhRegistro(healthDTO.getDhRegistro());
        health.setObservacao(healthDTO.getObservacao());
        return healthRepository.save(health);
    }

    public HealthDTO getHealth(Long healthId){
        return new HealthDTO(healthRepository.findById(healthId).orElseThrow(() -> new BaseBusinessException("NUTRITION_NOT_FOUND", "Nutricao nao encontrada", HttpStatus.FORBIDDEN)));
    }

    public List<HealthDTO> getHealths(String fromString, String toString, String userDocument){
        List<Health> healths = healthRepository.findAllDhRegistroBetween(stringToDate(fromString), stringToDate(toString), userDocument);
        List<HealthDTO> healthDTOS = new ArrayList<>();
        healths.forEach(health -> healthDTOS.add(new HealthDTO(health)));
        return healthDTOS;
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
