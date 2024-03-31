package com.internship.portal.user.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.internship.portal.user.model.Nutrition;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class NutritionDTO {
    @JsonProperty(value = "id")
    private Long id;

    @JsonProperty(value = "id_user")
    private String idUser;

    @JsonProperty(value = "dh_registro")
    private Date dhRegistro;

    @JsonProperty(value = "food", required = true)
    private String food;

    @JsonProperty(value = "time", required = true)
    private String time;

    @JsonProperty(value = "type", required = true)
    private String type;

    @JsonProperty(value = "observacao")
    private String observacao;

    public NutritionDTO(Nutrition nutrition) {
        this.dhRegistro = nutrition.getDhRegistro();
        this.id = nutrition.getId();
        this.idUser = nutrition.getIdUser();
        this.food = nutrition.getFood();
        this.time = nutrition.getTime();
        this.type = nutrition.getType();
        this.observacao = nutrition.getObservacao();
    }
}
