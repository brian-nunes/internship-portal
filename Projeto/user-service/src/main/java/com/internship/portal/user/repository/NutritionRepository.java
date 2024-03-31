package com.internship.portal.user.repository;

import com.internship.portal.user.model.Emotion;
import com.internship.portal.user.model.PhysicalActivity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface PhysicalActivityRepository extends JpaRepository<PhysicalActivity, Long> {

    @Query("select pa from PhysicalActivity pa where pa.idUser = :userId and pa.dhRegistro >= :from and pa.dhRegistro <= :to")
    List<PhysicalActivity> findAllDhRegistroBetween(@Param("from") Date from, @Param("to") Date to, @Param("userId") String userId);
}
