package com.internship.portal.physical.activity.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.internship.portal.physical.activity.model.Excercise;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ExcerciseDTO {
    @JsonProperty(value = "id")
    private Long id;

    @JsonProperty(value = "name")
    private String name;

    public ExcerciseDTO(Excercise excercise){

    }
}
