package com.internship.portal.schedule.model;

import com.internship.portal.schedule.dto.EmotionDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "emotion")
@NoArgsConstructor
public class Emotion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_user", nullable = false)
    private String idUser;


    @Column(name = "dh_registro", nullable = false)
    private Date dhRegistro;

    @Column(name = "status", nullable = false)
    private String status;

    @Column(name = "observacao")
    private String observacao;

    public Emotion(EmotionDTO emotionDTO){
        this.id = emotionDTO.getId();
        this.status = emotionDTO.getStatus();
        this.dhRegistro = emotionDTO.getDhRegistro();
        this.observacao = emotionDTO.getObservacao();
        this.idUser = emotionDTO.getIdUser();
    }
}
