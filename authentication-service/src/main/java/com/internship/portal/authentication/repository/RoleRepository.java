package com.internship.portal.authentication.repository;

import com.internship.portal.authentication.model.Role;
import com.internship.portal.authentication.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByRole(String role);
}
