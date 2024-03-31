package com.internship.portal.professional.repository;

import com.internship.portal.professional.model.Nota;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NoteRepository extends JpaRepository<Nota, Long> {
    Optional<Nota> findByIdUserAndIsProfessional(String idUser, String idProfessional);
}
