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
public class PhysicalActivityDTO {
    @JsonProperty(value = "id")
    private Long id;

    @JsonProperty(value = "id_user")
    private String idUser;

    @JsonProperty(value = "name")
    private String name;

    @JsonProperty(value = "created_at")
    private Date createdAt;

    @JsonProperty(value = "goal_date")
    private Date goalDate;

    @JsonProperty(value = "exercises")
    private Set<ExerciseDTO> exercises;

    public PhysicalActivityDTO(Medicine medicine) {
        this.id = medicine.getId();
        this.idUser = medicine.getIdUser();
        this.name = medicine.getName();
        this.createdAt = medicine.getCreatedAt();
        this.goalDate = medicine.getGoalDate();
        Set<ExerciseDTO> exercisesSet = new HashSet<>();
        medicine.getExercises().forEach(exercises -> exercisesSet.add(new ExerciseDTO(exercises)));
        this.exercises = exercisesSet;
    }
}
