package com.internship.portal.user.repository;

import com.internship.portal.user.model.Emotion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface EmotionRepository extends JpaRepository<Emotion, Long> {

    @Query("select e from Emotion e where e.idUser = :userId and e.dhRegistro >= :from and e.dhRegistro <= :to")
    List<Emotion> findAllDhRegistroBetween(@Param("from") Date from, @Param("to") Date to, @Param("userId") String userId);
}
