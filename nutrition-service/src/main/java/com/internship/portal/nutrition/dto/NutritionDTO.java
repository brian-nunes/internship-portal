package com.internship.portal.nutrition.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.internship.portal.nutrition.model.Meal;
import com.internship.portal.nutrition.model.Nutrition;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class NutritionDTO {
    @JsonProperty(value = "id")
    private Long id;

    @JsonProperty(value = "id_user")
    private String idUser;

    @JsonProperty(value = "name")
    private String name;

    @JsonProperty(value = "description")
    private String description;

    @JsonProperty(value = "start_date")
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date startDate;

    @JsonProperty(value = "end_date")
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date endDate;

    @JsonProperty(value = "meals")
    private Set<MealDTO> meals;

    public NutritionDTO(Nutrition nutrition) {
        this.id = nutrition.getId();
        this.idUser = nutrition.getIdUser();
        this.name = nutrition.getName();
        this.description = nutrition.getDescription();
        this.startDate = nutrition.getStartDate();
        this.endDate = nutrition.getEndDate();
        Set<MealDTO> mealsSet = new HashSet<>();
        nutrition.getMeals().forEach(meal -> mealsSet.add(new MealDTO(meal)));
        this.meals = mealsSet;
    }
}
