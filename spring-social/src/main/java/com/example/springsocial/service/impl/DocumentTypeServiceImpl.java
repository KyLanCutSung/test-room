package com.example.springsocial.service.impl;

import com.example.springsocial.model.document_types.DocumentTypes;
import com.example.springsocial.payload.document_type_payload.DocumentTypeDTO;
import com.example.springsocial.repository.DocumentTypeRepository;
import com.example.springsocial.service.DocumentTypeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class DocumentTypeServiceImpl implements DocumentTypeService {
    private final DocumentTypeRepository documentTypeRepository;

    @Override
    public List<DocumentTypeDTO> findAll() {
        List<DocumentTypeDTO> documentTypeDTOS = new ArrayList<>();
        List<DocumentTypes> documentTypes = documentTypeRepository.findAll();
        documentTypes.forEach(documentType -> {
            DocumentTypeDTO dto = new DocumentTypeDTO();
            BeanUtils.copyProperties(documentType, dto);
            documentTypeDTOS.add(dto);
        });
        return documentTypeDTOS;
    }
}
