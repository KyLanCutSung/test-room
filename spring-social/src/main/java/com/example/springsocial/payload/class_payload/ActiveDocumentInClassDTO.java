package com.example.springsocial.payload.class_payload;

import lombok.Data;

@Data
public class ActiveDocumentInClassDTO {
    private String className;
    private Long documentId;
    private String documentTitle;

}
