package com.internship.portal.professional.model;

import com.internship.portal.professional.dto.NoteDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

@Entity
@Getter
@Setter
@Table(name = "token")
@NoArgsConstructor
@AllArgsConstructor
public class Token {
    @Id
    @Column(name = "token", nullable = false, unique = true)
    private String token;

    @Column(name = "id_professional", nullable = false)
    private String idProfessional;
}
