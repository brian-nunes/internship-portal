package com.internship.portal.schedule.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.internship.portal.schedule.model.Appointment;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class EmotionDTO {
    @JsonProperty(value = "id")
    private Long id;

    @JsonProperty(value = "id_user")
    private String idUser;

    @JsonProperty(value = "dh_registro")
    private Date dhRegistro;

    @JsonProperty(value = "status", required = true)
    private String status;

    @JsonProperty(value = "observacao")
    private String observacao;

    public EmotionDTO(Appointment appointment) {
        this.dhRegistro = appointment.getDhRegistro();
        this.id = appointment.getId();
        this.idUser = appointment.getIdUser();
        this.status = appointment.getStatus();
        this.observacao = appointment.getObservacao();
    }
}
