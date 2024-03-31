package com.internship.portal.user.service;

import com.internship.portal.microservices.commons.exception.BaseBusinessException;
import com.internship.portal.user.dto.NutritionDTO;
import com.internship.portal.user.model.Nutrition;
import com.internship.portal.user.repository.NutritionRepository;
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
public class NutritionService {
    @Autowired
    private NutritionRepository nutritionRepository;

    public Nutrition postNutrition(NutritionDTO nutritionDTO){
        return nutritionRepository.save(new Nutrition(nutritionDTO));
    }

    public Nutrition putNutrition(NutritionDTO nutritionDTO){
        Nutrition nutrition = nutritionRepository.findById(nutritionDTO.getId()).orElseThrow(() -> new BaseBusinessException("NUTRITION_NOT_FOUND", "Nutricao nao encontrada", HttpStatus.FORBIDDEN));
        nutrition.setTime(nutritionDTO.getTime());
        nutrition.setFood(nutritionDTO.getFood());
        nutrition.setType(nutritionDTO.getType());
        nutrition.setDhRegistro(nutritionDTO.getDhRegistro());
        nutrition.setObservacao(nutritionDTO.getObservacao());
        return nutritionRepository.save(nutrition);
    }

    public NutritionDTO getNutrition(Long nutritionId){
        return new NutritionDTO(nutritionRepository.findById(nutritionId).orElseThrow(() -> new BaseBusinessException("NUTRITION_NOT_FOUND", "Nutricao nao encontrada", HttpStatus.FORBIDDEN)));
    }

    public List<NutritionDTO> getNutritions(String fromString, String toString, String userDocument){
        List<Nutrition> nutritions = nutritionRepository.findAllDhRegistroBetween(stringToDate(fromString), stringToDate(toString), userDocument);
        List<NutritionDTO> nutritionDTOS = new ArrayList<>();
        nutritions.forEach(nutrition -> nutritionDTOS.add(new NutritionDTO(nutrition)));
        return nutritionDTOS;
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
