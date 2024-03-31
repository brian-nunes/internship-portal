package com.internship.portal.physical.activity.service;

import com.internship.portal.physical.activity.model.Meal;
import com.internship.portal.physical.activity.model.Nutrition;
import com.internship.portal.physical.activity.repository.NutritionRepository;
import com.internship.portal.physical.activity.dto.PhysicalActivityDTO;
import com.internship.portal.microservices.commons.exception.BaseBusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@Slf4j
public class NutritionService {
    @Autowired
    private NutritionRepository nutritionRepository;

    public Nutrition postNutrition(PhysicalActivityDTO physicalActivityDTO){
        return nutritionRepository.save(new Nutrition(physicalActivityDTO));
    }

    public Nutrition putNutrition(PhysicalActivityDTO physicalActivityDTO){
        Nutrition nutrition = nutritionRepository.findById(physicalActivityDTO.getId()).orElseThrow(() -> new BaseBusinessException("NUTRITION_NOT_FOUND", "No nutrition with given id", HttpStatus.FORBIDDEN));
        nutrition.setDescription(physicalActivityDTO.getDescription());
        nutrition.setName(physicalActivityDTO.getName());
        nutrition.setIdUser(physicalActivityDTO.getIdUser());
        nutrition.setEndDate(physicalActivityDTO.getEndDate());
        nutrition.setStartDate(physicalActivityDTO.getStartDate());
        Set<Meal> mealSet = new HashSet<>();
        physicalActivityDTO.getMeals().forEach(mealDTO -> mealSet.add(new Meal(mealDTO)));
        nutrition.setMeals(mealSet);
        return nutritionRepository.save(nutrition);
    }

    public PhysicalActivityDTO getNutrition(String userDocument){
        Nutrition nutrition = nutritionRepository.findByIdUser(userDocument).orElseThrow(() -> new BaseBusinessException("NUTRITION_NOT_FOUND", "No nutrition with given document", HttpStatus.FORBIDDEN));
        return new PhysicalActivityDTO(nutrition);
    }
}
