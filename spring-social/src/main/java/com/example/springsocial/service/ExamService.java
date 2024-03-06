package com.example.springsocial.service;

import com.example.springsocial.model.exams.Exams;
import com.example.springsocial.payload.auth_payload.ApiResponse;
import com.example.springsocial.payload.exam_payload.ExamDTO;
import com.example.springsocial.payload.exam_result_payload.ExamResultDTO;
import com.example.springsocial.payload.quiz_payload.QuizDTO;

import java.util.List;

public interface ExamService {
    ApiResponse createExam(ExamDTO dto);
    ApiResponse changeStatusExam(ExamDTO dto);
    Exams getExamById(Long examId);
    List<QuizDTO> doExam(ExamResultDTO examResultDTO);
}

