package com.example.springsocial.service;

import com.example.springsocial.model.exams.Exams;
import com.example.springsocial.model.quiz.Quiz;
import com.example.springsocial.payload.exam_payload.ExamDTO;
import com.example.springsocial.payload.exam_result_payload.ExamResultDTO;
import com.example.springsocial.payload.quiz_payload.QuizDTO;

import java.util.List;

public interface ExamService {
    void createExam(ExamDTO dto);
    void changeStatusExam(ExamDTO dto);
    Exams getExamById(Long examId);
    List<QuizDTO> doExam(ExamResultDTO examResultDTO);
}

