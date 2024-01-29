package com.example.springsocial.controller;

import com.example.springsocial.payload.Response;
import com.example.springsocial.payload.document_payload.DocumentDTO;
import com.example.springsocial.payload.document_payload.MultiChoicesDocumentDTO;
import com.example.springsocial.service.DocumentService;
import com.example.springsocial.service.ExcelService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/document")
@RequiredArgsConstructor
public class DocumentController {
    private final DocumentService documentService;
    private final ExcelService excelService;

    @PostMapping(value = "/upload", consumes = { "multipart/form-data","application/json" })
    @PreAuthorize("hasRole('TEACHER')")
    public Response<DocumentDTO> upload(@RequestPart MultipartFile file,
                                        @RequestPart DocumentDTO documentDTO) throws IOException {
        return Response.ok(documentService.saveDocumentAndQuiz(file, documentDTO));
    }

    @GetMapping("/get/multiple-choices")
    public Response<MultiChoicesDocumentDTO> getMultipleChoicesDocument(@RequestParam("documentId") Long documentId) {
        return Response.ok(documentService.findMultiChoicesDocumentById(documentId));
    }
}
