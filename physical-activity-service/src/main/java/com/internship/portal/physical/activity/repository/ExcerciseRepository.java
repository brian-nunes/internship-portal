package com.internship.portal.physical.activity.repository;

import com.internship.portal.physical.activity.model.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExcerciseRepository extends JpaRepository<Exercise, Long> {

}
