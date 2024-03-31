package com.internship.portal.user.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.internship.portal.user.model.Emotion;
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

    public EmotionDTO(Emotion emotion) {
        this.dhRegistro = emotion.getDhRegistro();
        this.id = emotion.getId();
        this.idUser = emotion.getIdUser();
        this.status = emotion.getStatus();
        this.observacao = emotion.getObservacao();
    }
}
