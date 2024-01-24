package com.example.springsocial.service.impl;

import com.example.springsocial.repository.DocumentTypeRepository;
import com.example.springsocial.service.DocumentTypeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class DocumentTypeServiceImpl implements DocumentTypeService {
    private final DocumentTypeRepository documentTypeRepository;
}
