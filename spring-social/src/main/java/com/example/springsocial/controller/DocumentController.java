package com.example.springsocial.controller;

import com.example.springsocial.payload.document_payload.DocumentDTO;
import com.example.springsocial.payload.document_payload.MultiChoicesDocumentDTO;
import com.example.springsocial.service.DocumentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/document")
@RequiredArgsConstructor
public class DocumentController {
    private final DocumentService documentService;

    @PostMapping(value = "/upload-file", consumes = { "multipart/form-data","application/json" })
    @PreAuthorize("hasRole('TEACHER')")
    public ResponseEntity<DocumentDTO> upload(@RequestPart MultipartFile file,
                                              @RequestPart DocumentDTO documentDTO) throws IOException {
        return new ResponseEntity<>(documentService.saveDocument(file, documentDTO), HttpStatus.OK);
    }

    @PostMapping("/upload-document")
    @PreAuthorize("hasRole('TEACHER')")
    public ResponseEntity<DocumentDTO> upload(@RequestBody DocumentDTO documentDTO) {
        return new ResponseEntity<>(documentService.saveDocument(documentDTO),HttpStatus.OK);
    }

    @GetMapping("/get/multiple-choices")
    @PreAuthorize("hasRole('TEACHER')")
    public ResponseEntity<MultiChoicesDocumentDTO> getMultipleChoicesDocument(@RequestParam("documentId") Long documentId) {
        return new ResponseEntity<>(documentService.findMultiChoicesDocumentById(documentId),HttpStatus.OK);
    }
}
