package com.internship.portal.user.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.internship.portal.user.dto.EmotionDTO;
import com.internship.portal.user.dto.PhysicalActivityDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "physical_activity")
@NoArgsConstructor
public class PhysicalActivity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_user", nullable = false)
    private String idUser;


    @Column(name = "dh_registro", nullable = false)
    private Date dhRegistro;

    @Column(name = "intensity", nullable = false)
    private String intensity;

    @Column(name = "time", nullable = false)
    private String time;

    @Column(name = "type", nullable = false)
    private String type;

    @Column(name = "observacao")
    private String observacao;

    public PhysicalActivity(PhysicalActivityDTO physicalActivityDTO){
        this.id = physicalActivityDTO.getId();
        this.intensity = physicalActivityDTO.getIntensity();
        this.time = physicalActivityDTO.getTime();
        this.type = physicalActivityDTO.getType();
        this.dhRegistro = physicalActivityDTO.getDhRegistro();
        this.observacao = physicalActivityDTO.getObservacao();
        this.idUser = physicalActivityDTO.getIdUser();
    }
}
