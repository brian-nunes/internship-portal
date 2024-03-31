package com.internship.portal.authentication.repository;

import com.internship.portal.authentication.model.Role;
import com.internship.portal.authentication.model.User;
import com.internship.portal.authentication.model.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, Long> {
    Optional<UserRole> findByUserAndRole(Role role, User user);
}
