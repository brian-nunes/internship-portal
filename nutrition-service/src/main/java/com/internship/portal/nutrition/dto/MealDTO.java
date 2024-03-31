package com.internship.portal.nutrition.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.internship.portal.nutrition.model.Nutrition;
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

    @JsonProperty(value = "id_professional")
    private String idProfessional;

    @JsonProperty(value = "time")
    private Date time;


    public NutritionDTO(Nutrition appointment) {
        this.time = appointment.getTime();
        this.id = appointment.getId();
        this.idUser = appointment.getIdUser();
        this.idProfessional = appointment.getIdProfessional();
    }
}
