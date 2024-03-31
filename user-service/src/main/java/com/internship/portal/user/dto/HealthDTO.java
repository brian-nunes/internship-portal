package com.internship.portal.user.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.internship.portal.user.model.Health;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class HealthDTO {
    @JsonProperty(value = "id")
    private Long id;

    @JsonProperty(value = "id_user")
    private String idUser;

    @JsonProperty(value = "dh_registro")
    private Date dhRegistro;

    @JsonProperty(value = "weight")
    private String weight;

    @JsonProperty(value = "oxigen")
    private String oxigen;

    @JsonProperty(value = "blood_pressure")
    private String bloodPressure;

    @JsonProperty(value = "observacao")
    private String observacao;

    public HealthDTO(Health health) {
        this.dhRegistro = health.getDhRegistro();
        this.id = health.getId();
        this.idUser = health.getIdUser();
        this.weight = health.getWeight();
        this.oxigen = health.getOxigen();
        this.bloodPressure = health.getBloodPressure();
        this.observacao = health.getObservacao();
    }
}
