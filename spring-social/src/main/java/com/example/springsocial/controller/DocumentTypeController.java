package com.example.springsocial.controller;

import com.example.springsocial.service.DocumentTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/document-type")
@RequiredArgsConstructor
public class DocumentTypeController {
    private final DocumentTypeService documentTypeService;
}
