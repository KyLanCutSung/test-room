package com.example.springsocial.service;

import com.example.springsocial.payload.document_type_payload.DocumentTypeDTO;

import java.util.List;

public interface DocumentTypeService {
    List<DocumentTypeDTO> findAll();
}
