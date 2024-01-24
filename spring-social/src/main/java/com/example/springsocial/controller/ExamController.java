package com.example.springsocial.controller;

import com.example.springsocial.service.ExamService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/exam")
@RequiredArgsConstructor
public class ExamController {
    private final ExamService examService;
}
