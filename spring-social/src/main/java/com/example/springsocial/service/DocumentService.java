package com.example.springsocial.service;

import com.example.springsocial.model.documents.Documents;
import com.example.springsocial.payload.document_payload.DocumentDTO;
import com.example.springsocial.payload.document_payload.MultiChoicesDocumentDTO;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface DocumentService {
    DocumentDTO saveDocument(MultipartFile file, DocumentDTO documentDTO) throws IOException;
    MultiChoicesDocumentDTO findMultiChoicesDocumentById(Long documentId);
    DocumentDTO saveDocument(DocumentDTO documentDTO);
}
