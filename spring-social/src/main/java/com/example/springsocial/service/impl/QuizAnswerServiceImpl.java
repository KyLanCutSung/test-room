package com.example.springsocial.service.impl;

import com.example.springsocial.repository.QuizAnswerRepository;
import com.example.springsocial.service.QuizAnswerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class QuizAnswerServiceImpl implements QuizAnswerService {
    private final QuizAnswerRepository quizAnswerRepository;
}
