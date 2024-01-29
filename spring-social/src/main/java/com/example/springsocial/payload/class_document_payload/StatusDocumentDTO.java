package com.example.springsocial.payload.class_document_payload;

import lombok.Data;

import java.util.List;

@Data
public class StatusDocumentDTO {
    private Long ownerId;
    private List<ClassDocumentDTO> classDocumentDTOS;
}
