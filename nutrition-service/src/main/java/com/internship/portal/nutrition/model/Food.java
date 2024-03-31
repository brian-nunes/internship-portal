package com.internship.portal.nutrition.model;

import com.internship.portal.nutrition.dto.NutritionDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

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

    public Meal(NutritionDTO nutritionDTO){
        this.id = nutritionDTO.getId();
        this.time = nutritionDTO.getTime();
        this.idProfessional = nutritionDTO.getIdProfessional();
        this.idUser = nutritionDTO.getIdUser();
    }
}
