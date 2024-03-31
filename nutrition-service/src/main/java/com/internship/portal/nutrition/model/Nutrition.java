package com.internship.portal.nutrition.model;

import com.internship.portal.nutrition.dto.AppointmentDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "appointment")
@NoArgsConstructor
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_professional", nullable = false)
    private String idProfessional;

    @Column(name = "id_user")
    private String idUser;

    @Column(name = "time", nullable = false)
    private Date time;
    public Appointment(AppointmentDTO appointmentDTO){
        this.id = appointmentDTO.getId();
        this.time = appointmentDTO.getTime();
        this.idProfessional = appointmentDTO.getIdProfessional();
        this.idUser = appointmentDTO.getIdUser();
    }

    public Appointment(String idUser, String idProfessional, Date time){
        this.time = time;
        this.idProfessional = idProfessional;
        this.idUser = idUser;
    }

    public Appointment(String idProfessional, Date time){
        this.time = time;
        this.idProfessional = idProfessional;
    }
}
