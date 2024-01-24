package com.example.springsocial.repository;

import com.example.springsocial.model.exams.Exams;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExamRepository extends JpaRepository<Exams,Long> {
}
