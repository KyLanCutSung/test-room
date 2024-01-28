package com.example.springsocial.repository;

import com.example.springsocial.model.roles.RoleProvider;
import com.example.springsocial.model.roles.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolesRepository extends JpaRepository<Roles, Integer> {
    Roles findByRoleName(RoleProvider roleName);
}
