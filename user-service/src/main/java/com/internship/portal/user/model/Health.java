package com.internship.portal.user.model;

import com.internship.portal.user.dto.HealthDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "health")
@NoArgsConstructor
public class Health {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_user", nullable = false)
    private String idUser;


    @Column(name = "dh_registro", nullable = false)
    private Date dhRegistro;

    @Column(name = "weight", nullable = false)
    private String weight;

    @Column(name = "oxigen", nullable = false)
    private String oxigen;

    @Column(name = "blood_pressure", nullable = false)
    private String bloodPressure;

    @Column(name = "observacao")
    private String observacao;

    public Health(HealthDTO healthDTO){
        this.id = healthDTO.getId();
        this.weight = healthDTO.getWeight();
        this.oxigen = healthDTO.getOxigen();
        this.bloodPressure = healthDTO.getBloodPressure();
        this.dhRegistro = healthDTO.getDhRegistro();
        this.observacao = healthDTO.getObservacao();
        this.idUser = healthDTO.getIdUser();
    }
}
