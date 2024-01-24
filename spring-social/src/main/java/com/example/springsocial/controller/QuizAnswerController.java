package com.example.springsocial.controller;

import com.example.springsocial.service.QuizAnswerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/quiz-answer")
@RequiredArgsConstructor
public class QuizAnswerController {
    private final QuizAnswerService quizAnswerService;
}
