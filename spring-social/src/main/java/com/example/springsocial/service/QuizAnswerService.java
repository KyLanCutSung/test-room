package com.example.springsocial.service;

import com.example.springsocial.model.quiz_answer.QuizAnswer;

import java.util.List;

public interface QuizAnswerService {
    void saveAll(List<QuizAnswer> quizAnswers);
}
