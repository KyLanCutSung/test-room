package com.example.springsocial.service.impl;

import com.example.springsocial.repository.DocumentExamRepository;
import com.example.springsocial.service.DocumentExamService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DocumentExamServiceImpl implements DocumentExamService {
    private final DocumentExamRepository documentExamRepository;
}
