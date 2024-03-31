package com.internship.portal.user.model;

import com.internship.portal.user.dto.NutritionDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

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


    @Column(name = "dh_registro", nullable = false)
    private Date dhRegistro;

    @Column(name = "food", nullable = false)
    private String food;

    @Column(name = "time", nullable = false)
    private String time;

    @Column(name = "type", nullable = false)
    private String type;

    @Column(name = "observacao")
    private String observacao;

    public Nutrition(NutritionDTO nutritionDTO){
        this.id = nutritionDTO.getId();
        this.food = nutritionDTO.getFood();
        this.time = nutritionDTO.getTime();
        this.type = nutritionDTO.getType();
        this.dhRegistro = nutritionDTO.getDhRegistro();
        this.observacao = nutritionDTO.getObservacao();
        this.idUser = nutritionDTO.getIdUser();
    }
}
