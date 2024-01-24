package com.example.springsocial.repository;

import com.example.springsocial.model.exam_type.ExamType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExamTypeRepository extends JpaRepository<ExamType, Integer> {
}
