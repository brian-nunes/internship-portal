package com.internship.portal.user.service;

import com.internship.portal.microservices.commons.exception.BaseBusinessException;
import com.internship.portal.user.dto.PhysicalActivityDTO;
import com.internship.portal.user.model.PhysicalActivity;
import com.internship.portal.user.repository.PhysicalActivityRepository;
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
public class PhysicalActivityService {
    @Autowired
    private PhysicalActivityRepository physicalActivityRepository;

    public PhysicalActivity postPhysicalActivity(PhysicalActivityDTO physicalActivityDTO){
        return physicalActivityRepository.save(new PhysicalActivity(physicalActivityDTO));
    }

    public PhysicalActivity putPhysicalActivity(PhysicalActivityDTO physicalActivityDTO){
        PhysicalActivity physicalActivity = physicalActivityRepository.findById(physicalActivityDTO.getId()).orElseThrow(() -> new BaseBusinessException("PHYSISCAL_ACTIVITY_NOT_FOUND", "Atividade nao encontrada", HttpStatus.FORBIDDEN));
        physicalActivity.setTime(physicalActivityDTO.getTime());
        physicalActivity.setIntensity(physicalActivityDTO.getIntensity());
        physicalActivity.setType(physicalActivityDTO.getType());
        physicalActivity.setDhRegistro(physicalActivityDTO.getDhRegistro());
        physicalActivity.setObservacao(physicalActivityDTO.getObservacao());
        return physicalActivityRepository.save(physicalActivity);
    }

    public PhysicalActivityDTO getPhysicalActivity(Long physicalActivityId){
        return new PhysicalActivityDTO(physicalActivityRepository.findById(physicalActivityId).orElseThrow(() -> new BaseBusinessException("PHYSISCAL_ACTIVITY_NOT_FOUND", "Atividade nao encontrada", HttpStatus.FORBIDDEN)));
    }

    public List<PhysicalActivityDTO> getPhysicalActivitys(String fromString, String toString, String userDocument){
        List<PhysicalActivity> physicalActivitys = physicalActivityRepository.findAllDhRegistroBetween(stringToDate(fromString), stringToDate(toString), userDocument);
        List<PhysicalActivityDTO> physicalActivityDTOS = new ArrayList<>();
        physicalActivitys.forEach(physicalActivity -> physicalActivityDTOS.add(new PhysicalActivityDTO(physicalActivity)));
        return physicalActivityDTOS;
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
