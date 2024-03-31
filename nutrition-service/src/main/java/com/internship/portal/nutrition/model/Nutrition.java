package com.internship.portal.nutrition.model;

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
@Table(name = "nutrition")
@NoArgsConstructor
public class Nutrition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_user", nullable = false)
    private String idUser;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "start_date", nullable = false)
    private Date startDate;

    @Column(name = "end_date", nullable = false)
    private Date endDate;

    @OneToMany(mappedBy = "nutrition")
    private Set<Meal> meals;

    public Nutrition(NutritionDTO nutritionDTO){
        this.id = nutritionDTO.getId();
        this.idUser = nutritionDTO.getIdUser();
        this.name = nutritionDTO.getName();
        this.description = nutritionDTO.getDescription();
        this.startDate = nutritionDTO.getStartDate();
        this.endDate = nutritionDTO.getEndDate();
        Set<Meal> mealsSet = new HashSet<>();
        nutritionDTO.getMeals().forEach(mealDTO -> mealsSet.add(new Meal(mealDTO)));
        this.meals = mealsSet;
    }
}
