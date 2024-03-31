package com.internship.portal.physical.activity.model;

import com.internship.portal.physical.activity.dto.ExerciseDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "exercise")
@NoArgsConstructor
public class Exercise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "reps_min", nullable = false)
    private Integer repsMin;

    @Column(name = "reps_max", nullable = false)
    private Integer repsMax;

    @Column(name = "series", nullable = false)
    private Integer series;

    @Column(name = "observation", nullable = false)
    private String observation;

    @ManyToOne(fetch = FetchType.LAZY)
    private PhysicalActivity physicalActivity;

    public Exercise(ExerciseDTO exerciseDTO){
        this.id = exerciseDTO.getId();
        this.name = exerciseDTO.getName();
        this.repsMin = exerciseDTO.getRepsMin();
        this.repsMax = exerciseDTO.getRepsMax();
        this.series = exerciseDTO.getSeries();
        this.observation = exerciseDTO.getObservation();
    }
}
