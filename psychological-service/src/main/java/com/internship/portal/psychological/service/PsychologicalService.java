package com.internship.portal.psychological.service;

import com.internship.portal.psychological.model.Exercise;
import com.internship.portal.psychological.model.PhysicalActivity;
import com.internship.portal.psychological.repository.PhysicalActivityRepository;
import com.internship.portal.psychological.dto.PhysicalActivityDTO;
import com.internship.portal.microservices.commons.exception.BaseBusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@Slf4j
public class PhysicalActivityService {
    @Autowired
    private PhysicalActivityRepository physicalActivityRepository;

    public PhysicalActivity postPhysicalActivity(PhysicalActivityDTO physicalActivityDTO){
        return physicalActivityRepository.save(new PhysicalActivity(physicalActivityDTO));
    }

    public PhysicalActivity putPhysicalActivity(PhysicalActivityDTO physicalActivityDTO){
        PhysicalActivity physicalActivity = physicalActivityRepository.findById(physicalActivityDTO.getId()).orElseThrow(() -> new BaseBusinessException("PHYSICAL_ACTIVITY_NOT_FOUND", "No Physical Activity with given id", HttpStatus.FORBIDDEN));
        physicalActivity.setName(physicalActivityDTO.getName());
        physicalActivity.setIdUser(physicalActivityDTO.getIdUser());
        physicalActivity.setCreatedAt(physicalActivityDTO.getCreatedAt());
        physicalActivity.setGoalDate(physicalActivityDTO.getGoalDate());
        Set<Exercise> exerciseSet = new HashSet<>();
        physicalActivityDTO.getExcercises().forEach(exerciseDTO -> exerciseSet.add(new Exercise(exerciseDTO)));
        physicalActivity.setExercises(exerciseSet);
        return physicalActivityRepository.save(physicalActivity);
    }

    public PhysicalActivityDTO getPhysicalActivity(String userDocument){
        PhysicalActivity physicalActivity = physicalActivityRepository.findByIdUser(userDocument).orElseThrow(() -> new BaseBusinessException("NUTRITION_NOT_FOUND", "No physicalActivity with given document", HttpStatus.FORBIDDEN));
        return new PhysicalActivityDTO(physicalActivity);
    }
}
