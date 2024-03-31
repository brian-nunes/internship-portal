package com.internship.portal.psychological.model;

import com.internship.portal.psychological.dto.MedicineDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "medicine")
@NoArgsConstructor
public class Medicine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_user", nullable = false)
    private String idUser;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "created_at", nullable = false)
    private Date createdAt;

    @Column(name = "instructions", nullable = false)
    private String instructions;

    public Medicine(MedicineDTO medicineDTO) {
        this.id = medicineDTO.getId();
        this.idUser = medicineDTO.getIdUser();
        this.name = medicineDTO.getName();
        this.createdAt = medicineDTO.getCreatedAt();
        this.instructions = medicineDTO.getInstructions();
    }
}
