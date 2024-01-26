package com.example.springsocial.controller;

import com.example.springsocial.payload.Response;
import com.example.springsocial.payload.document_type_payload.DocumentTypeDTO;
import com.example.springsocial.service.DocumentTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/document-type")
@RequiredArgsConstructor
public class DocumentTypeController {
    private final DocumentTypeService documentTypeService;
    @GetMapping("/getAll")
    public Response<List<DocumentTypeDTO>> getAll() {
        return Response.ok(documentTypeService.findAll());
    }
}
