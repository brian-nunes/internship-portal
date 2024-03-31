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
@Table(name = "appointment")
@NoArgsConstructor
public class Nutrition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "start_date", nullable = false)
    private Date start_date;

    @Column(name = "end_date", nullable = false)
    private Date end_date;


    public Nutrition(NutritionDTO nutritionDTO){
        this.id = nutritionDTO.getId();
        this.time = nutritionDTO.getTime();
        this.idProfessional = nutritionDTO.getIdProfessional();
        this.idUser = nutritionDTO.getIdUser();
    }
}
