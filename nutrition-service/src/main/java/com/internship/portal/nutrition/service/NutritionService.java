package com.internship.portal.nutrition.service;

import com.internship.portal.nutrition.dto.NutritionDTO;
import com.internship.portal.nutrition.model.Meal;
import com.internship.portal.nutrition.model.Nutrition;
import com.internship.portal.microservices.commons.exception.BaseBusinessException;
import com.internship.portal.nutrition.repository.NutritionRepository;
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

    public Nutrition postNutrition(NutritionDTO nutritionDTO){
        return nutritionRepository.save(new Nutrition(nutritionDTO));
    }

    public Nutrition putNutrition(NutritionDTO nutritionDTO){
        Nutrition nutrition = nutritionRepository.findById(nutritionDTO.getId()).orElseThrow(() -> new BaseBusinessException("NUTRITION_NOT_FOUND", "No nutrition with given id", HttpStatus.FORBIDDEN));
        nutrition.setDescription(nutritionDTO.getDescription());
        nutrition.setName(nutritionDTO.getName());
        nutrition.setIdUser(nutritionDTO.getIdUser());
        nutrition.setEndDate(nutritionDTO.getEndDate());
        nutrition.setStartDate(nutritionDTO.getStartDate());
        Set<Meal> mealSet = new HashSet<>();
        nutritionDTO.getMeals().forEach(mealDTO -> mealSet.add(new Meal(mealDTO)));
        nutrition.setMeals(mealSet);
        return nutritionRepository.save(nutrition);
    }

    public NutritionDTO getNutrition(String userDocument){
        Nutrition nutrition = nutritionRepository.findByIdUser(userDocument).orElseThrow(() -> new BaseBusinessException("NUTRITION_NOT_FOUND", "No nutrition with given document", HttpStatus.FORBIDDEN));
        return new NutritionDTO(nutrition);
    }
}
