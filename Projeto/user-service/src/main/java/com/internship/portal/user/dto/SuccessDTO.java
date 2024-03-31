package com.internship.portal.user.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class SuccessDTO {
    @JsonProperty(value = "id")
    private Long id;

    @JsonProperty(value = "studentName", required = true)
    private String studentName;

    @JsonProperty(value = "grade", required = true)
    private Double grade;
}
