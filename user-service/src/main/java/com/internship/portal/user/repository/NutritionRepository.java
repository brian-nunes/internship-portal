package com.internship.portal.user.repository;

import com.internship.portal.user.model.Nutrition;
import com.internship.portal.user.model.PhysicalActivity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface NutritionRepository extends JpaRepository<Nutrition, Long> {

    @Query("select n from Nutrition n where n.idUser = :userId and n.dhRegistro >= :from and n.dhRegistro <= :to")
    List<Nutrition> findAllDhRegistroBetween(@Param("from") Date from, @Param("to") Date to, @Param("userId") String userId);
}
