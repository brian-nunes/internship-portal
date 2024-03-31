package com.internship.portal.user.repository;

import com.internship.portal.user.model.Emotion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReportRepository extends JpaRepository<Emotion, Long> {
}
