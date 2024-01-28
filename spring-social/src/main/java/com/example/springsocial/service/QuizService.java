package com.example.springsocial.service;

import com.example.springsocial.model.quiz.Quiz;

import java.util.List;

public interface QuizService {
    List<Quiz> saveAll(List<Quiz> quizzes);
    Quiz save(Quiz quiz);
    List<Quiz> findAll();
}
