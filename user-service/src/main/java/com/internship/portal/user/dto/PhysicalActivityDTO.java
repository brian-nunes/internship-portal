package com.internship.portal.user.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.internship.portal.user.model.Emotion;
import com.internship.portal.user.model.PhysicalActivity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class PhysicalActivityDTO {
    @JsonProperty(value = "id")
    private Long id;

    @JsonProperty(value = "id_user")
    private String idUser;

    @JsonProperty(value = "dh_registro")
    @DateTimeFormat(pattern = "dd-MM-yyyy hh:mm")
    private Date dhRegistro;

    @JsonProperty(value = "intensity", required = true)
    private String intensity;

    @JsonProperty(value = "time", required = true)
    private String time;

    @JsonProperty(value = "type", required = true)
    private String type;

    @JsonProperty(value = "observacao")
    private String observacao;

    public PhysicalActivityDTO(PhysicalActivity physicalActivity) {
        this.dhRegistro = physicalActivity.getDhRegistro();
        this.id = physicalActivity.getId();
        this.idUser = physicalActivity.getIdUser();
        this.intensity = physicalActivity.getIntensity();
        this.time = physicalActivity.getTime();
        this.type = physicalActivity.getType();
        this.observacao = physicalActivity.getObservacao();
    }
}
