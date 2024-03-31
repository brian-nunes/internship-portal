package com.internship.portal.user.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "report")
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "student_name", nullable = false)
    private String studentName;

    @Column(name = "grade", nullable = false)
    private Double grade;

    public Report() {
        super();
    }

    public Report(String studentName, Double grade) {
        this.studentName = studentName;
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "Report{" +
                "id=" + id +
                ", studentName='" + studentName + '\'' +
                ", grade=" + grade +
                '}';
    }
}
