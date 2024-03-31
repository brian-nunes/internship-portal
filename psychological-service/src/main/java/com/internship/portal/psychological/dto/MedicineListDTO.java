package com.internship.portal.psychological.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.internship.portal.psychological.model.Medicine;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class MedicineDTO {
    @JsonProperty(value = "id")
    private Long id;

    @JsonProperty(value = "id_user")
    private String idUser;

    @JsonProperty(value = "name")
    private String name;

    @JsonProperty(value = "created_at")
    private Date createdAt;

    @JsonProperty(value = "instructions")
    private String instructions;

    public MedicineDTO(Medicine medicine) {
        this.id = medicine.getId();
        this.idUser = medicine.getIdUser();
        this.name = medicine.getName();
        this.createdAt = medicine.getCreatedAt();
        this.instructions = medicine.getInstructions();
    }
}
