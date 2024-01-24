package com.example.springsocial.repository;

import com.example.springsocial.model.exam_quiz.ExamQuiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExamQuizRepository extends JpaRepository<ExamQuiz, Long> {
}
