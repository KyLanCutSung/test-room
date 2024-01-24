package com.example.springsocial.controller;

import com.example.springsocial.service.ExamTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/exam-type")
@RequiredArgsConstructor
public class ExamTypeController {
    private final ExamTypeService examTypeService;
}
