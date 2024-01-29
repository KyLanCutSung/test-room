package com.example.springsocial.service.impl;

import com.example.springsocial.model.quiz.Quiz;
import com.example.springsocial.repository.QuizRepository;
import com.example.springsocial.service.QuizService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class QuizServiceImpl implements QuizService {
    private final QuizRepository quizRepository;

}
