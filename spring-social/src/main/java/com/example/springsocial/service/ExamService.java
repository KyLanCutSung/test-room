package com.example.springsocial.service;

import com.example.springsocial.model.exams.Exams;
import com.example.springsocial.payload.exam_payload.ExamDTO;

public interface ExamService {
    void createExam(ExamDTO dto);
    void changeStatusExam(ExamDTO dto);
    Exams getExamById(Long examId);
}

