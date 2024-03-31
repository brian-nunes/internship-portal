package com.internship.portal.professional.repository;

import com.internship.portal.professional.model.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TokenRepository extends JpaRepository<Token, Long> {
    boolean existsByToken(String token);

    Optional<Token> findByTokenIgnoreCase(String token);
}
