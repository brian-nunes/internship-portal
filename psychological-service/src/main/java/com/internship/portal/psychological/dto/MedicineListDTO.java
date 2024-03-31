package com.internship.portal.psychological.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import java.util.List;

@Builder
public class MedicineListDTO {
    @JsonProperty(value = "medicine_list")
    private List<MedicineDTO> medicineList;
}
