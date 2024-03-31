package com.internship.portal.nutrition.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.internship.portal.nutrition.model.Food;
import com.internship.portal.nutrition.model.Meal;
import com.internship.portal.nutrition.model.Nutrition;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class MealDTO {
    @JsonProperty(value = "id")
    private Long id;

    @JsonProperty(value = "name")
    private String name;

    @JsonProperty(value = "foods")
    private Set<FoodDTO> foods;

    public MealDTO(Meal meal) {
        this.id = meal.getId();
        this.name = meal.getName();
        Set<FoodDTO> foodsSet = new HashSet<>();
        meal.getFoods().forEach(food -> foodsSet.add(new FoodDTO(food)));
        this.foods = foodsSet;
    }
}
