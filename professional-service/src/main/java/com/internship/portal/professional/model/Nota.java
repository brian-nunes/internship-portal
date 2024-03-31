package com.internship.portal.professional.model;

import com.internship.portal.professional.dto.NoteDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "note")
@NoArgsConstructor
public class Nota {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_user", nullable = false)
    private String idUser;

    @Column(name = "id_professional", nullable = false)
    private String idProfessional;

    @Column(name = "nota")
    private String nota;

    public Nota(NoteDTO noteDTO){
        this.id = noteDTO.getId();
        this.nota = noteDTO.getNota();
        this.idUser = noteDTO.getIdUser();
        this.idProfessional = noteDTO.getIdProfessional();
    }
}
