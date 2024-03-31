package com.internship.portal.physical.activity.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.internship.portal.physical.activity.model.Exercise;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ExerciseDTO {
    @JsonProperty(value = "id")
    private Long id;

    @JsonProperty(value = "name")
    private String name;

    @JsonProperty(value = "reps_min")
    private Integer repsMin;

    @JsonProperty(value = "reps_max")
    private Integer repsMax;

    @JsonProperty(value = "series")
    private Integer series;

    @JsonProperty(value = "observation")
    private String observation;
    public ExerciseDTO(Exercise exercise){
        this.id = exercise.getId();
        this.name = exercise.getName();
        this.repsMin = exercise.getRepsMin();
        this.repsMax = exercise.getRepsMax();
        this.series = exercise.getSeries();
        this.observation = exercise.getObservation();
    }
}
