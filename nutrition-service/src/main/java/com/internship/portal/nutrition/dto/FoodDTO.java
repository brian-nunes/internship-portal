package com.internship.portal.nutrition.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.internship.portal.nutrition.model.Food;
import com.internship.portal.nutrition.model.Nutrition;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class FoodDTO {
    @JsonProperty(value = "id")
    private Long id;

    @JsonProperty(value = "name")
    private String name;

    @JsonProperty(value = "quantity")
    private String quantity;

    public FoodDTO(Food food) {
        this.id = food.getId();
        this.name = food.getName();
        this.quantity = food.getQuantity();
    }
}
