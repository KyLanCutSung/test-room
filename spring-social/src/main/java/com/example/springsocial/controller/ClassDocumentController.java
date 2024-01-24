package com.example.springsocial.controller;

import com.example.springsocial.service.ClassDocumentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/class-document")
@RequiredArgsConstructor
public class ClassDocumentController {
    private final ClassDocumentService classDocumentService;
}
