package com.example.springsocial.service.impl;


import com.example.springsocial.repository.ExamResultRepository;
import com.example.springsocial.service.ExamResultService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class ExamResultServiceImpl implements ExamResultService {
    private final ExamResultRepository examResultRepository;

}
