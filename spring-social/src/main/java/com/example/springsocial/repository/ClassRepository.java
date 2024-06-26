package com.example.springsocial.repository;

import com.example.springsocial.model.classes.Classes;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClassRepository extends JpaRepository<Classes, Long> {
    Optional<Classes> findByClassIdAndOwnerId(Long classId, Long ownerId);
    Optional<Classes> findByClassCode(String classCode);
    Page<Classes> findAllByOwnerId(Long ownerId, Pageable pageable);
    boolean existsByOwnerId(Long ownerId);
}
