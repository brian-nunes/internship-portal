package com.internship.portal.professional.repository;

import com.internship.portal.professional.model.Link;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LinkRepository extends JpaRepository<Link, Long> {
    Optional<Link> findByIdProfessionalAndIdUser(String idProfessional, String idUser);

    List<Link> findByIdUser(String idUser);

    List<Link> findByIdProfessional(String idProfessional);
}
