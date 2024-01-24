package com.example.springsocial.service.impl;

import com.example.springsocial.repository.ExamQuizRepository;
import com.example.springsocial.service.ExamQuizService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class ExamQuizServiceImpl implements ExamQuizService {
    private final ExamQuizRepository examQuizRepository;
}
