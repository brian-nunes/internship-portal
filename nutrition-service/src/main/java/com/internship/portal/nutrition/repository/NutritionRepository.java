package com.internship.portal.nutrition.repository;

import com.internship.portal.nutrition.model.Nutrition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NutritionRepository extends JpaRepository<Nutrition, Long> {

    Optional<Nutrition> findByIdUser(String idUser);
}
