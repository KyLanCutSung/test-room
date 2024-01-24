package com.example.springsocial.controller;

import com.example.springsocial.service.ExamQuizService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/exam-quiz")
@RequiredArgsConstructor
public class ExamQuizController {
    private final ExamQuizService examQuizService;
}
