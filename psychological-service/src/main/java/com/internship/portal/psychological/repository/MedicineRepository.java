package com.internship.portal.psychological.repository;

import com.internship.portal.psychological.model.Medicine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PhysicalActivityRepository extends JpaRepository<Medicine, Long> {

    Optional<Medicine> findByIdUser(String idUser);
}
