package com.internship.portal.professional.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "link")
@NoArgsConstructor
@AllArgsConstructor
public class Link {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "id_professional", nullable = false)
    private String idProfessional;

    @Column(name = "id_user", nullable = false)
    private String idUser;

    public Link(String idProfessional, String idUser) {
        this.idProfessional = idProfessional;
        this.idUser = idUser;
    }
}
