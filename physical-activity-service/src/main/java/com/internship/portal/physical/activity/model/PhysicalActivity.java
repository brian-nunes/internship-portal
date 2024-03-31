package com.internship.portal.physical.activity.model;

import com.internship.portal.physical.activity.dto.PhysicalActivityDTO;
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

    public Nutrition(PhysicalActivityDTO physicalActivityDTO){
        this.id = physicalActivityDTO.getId();
        this.idUser = physicalActivityDTO.getIdUser();
        this.name = physicalActivityDTO.getName();
        this.description = physicalActivityDTO.getDescription();
        this.startDate = physicalActivityDTO.getStartDate();
        this.endDate = physicalActivityDTO.getEndDate();
        Set<Meal> mealsSet = new HashSet<>();
        physicalActivityDTO.getMeals().forEach(mealDTO -> mealsSet.add(new Meal(mealDTO)));
        this.meals = mealsSet;
    }
}
