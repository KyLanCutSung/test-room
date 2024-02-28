package com.example.springsocial.repository;

import com.example.springsocial.model.exams.Exams;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ExamRepository extends JpaRepository<Exams,Long> {
    Optional<Exams> findByExamIdAndClassIdAndOwnerId(Long examId, Long classId, Long ownerId);
}
