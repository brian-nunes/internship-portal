package com.internship.portal.physical.activity.repository;

import com.internship.portal.physical.activity.model.PhysicalActivity;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PhysicalActivityRepository extends JpaRepository<PhysicalActivity, Long> {

    Optional<PhysicalActivity> findByIdUser(String idUser);
}
