package com.internship.portal.physical.activity.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.internship.portal.physical.activity.model.PhysicalActivity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

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
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date createdAt;

    @JsonProperty(value = "goal_date")
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date goalDate;

    @JsonProperty(value = "exercises")
    private Set<ExerciseDTO> exercises;

    public PhysicalActivityDTO(PhysicalActivity physicalActivity) {
        this.id = physicalActivity.getId();
        this.idUser = physicalActivity.getIdUser();
        this.name = physicalActivity.getName();
        this.createdAt = physicalActivity.getCreatedAt();
        this.goalDate = physicalActivity.getGoalDate();
        Set<ExerciseDTO> exercisesSet = new HashSet<>();
        physicalActivity.getExercises().forEach(exercises -> exercisesSet.add(new ExerciseDTO(exercises)));
        this.exercises = exercisesSet;
    }
}
