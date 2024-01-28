package com.example.springsocial.service;

import com.example.springsocial.payload.document_payload.DocumentDTO;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface DocumentService {
    DocumentDTO saveDocumentAndQuiz(MultipartFile file, DocumentDTO documentDTO) throws IOException;
}
