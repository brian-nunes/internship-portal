package com.internship.portal.nutrition.model;

import com.internship.portal.nutrition.dto.MealDTO;
import com.internship.portal.nutrition.dto.NutritionDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "meal")
@NoArgsConstructor
public class Meal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    private Nutrition nutrition;

    @OneToMany(mappedBy = "meal")
    private Set<Food> foods;

    public Meal(MealDTO mealDTO){
        this.id = mealDTO.getId();
        this.name = mealDTO.getName();
        Set<Food> foodsSet = new HashSet<>();
        mealDTO.getFoods().forEach(foodDTO -> foodsSet.add(new Food(foodDTO)));
        this.foods = foodsSet;
    }
}
