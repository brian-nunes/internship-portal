package com.internship.portal.psychological.model;

import com.internship.portal.psychological.dto.PhysicalActivityDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "physical_activity")
@NoArgsConstructor
public class PhysicalActivity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_user", nullable = false)
    private String idUser;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "created_at", nullable = false)
    private Date createdAt;

    @Column(name = "goal_date", nullable = false)
    private Date goalDate;

    @OneToMany(mappedBy = "physicalActivity")
    private Set<Exercise> exercises;

    public PhysicalActivity(PhysicalActivityDTO physicalActivityDTO){
        this.id = physicalActivityDTO.getId();
        this.idUser = physicalActivityDTO.getIdUser();
        this.name = physicalActivityDTO.getName();
        this.createdAt = physicalActivityDTO.getCreatedAt();
        this.goalDate = physicalActivityDTO.getGoalDate();
        Set<Exercise> exerciseSet = new HashSet<>();
        physicalActivityDTO.getExcercises().forEach(excerciseDTO -> exerciseSet.add(new Exercise(excerciseDTO)));
        this.exercises = exerciseSet;
    }
}
