package com.example.springsocial.service.impl;

import com.example.springsocial.repository.ExamTypeRepository;
import com.example.springsocial.service.ExamTypeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class ExamTypeServiceImpl implements ExamTypeService {
    private final ExamTypeRepository examTypeRepository;
}
