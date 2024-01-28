package com.example.springsocial.payload.document_payload;

import lombok.Data;

@Data
public class DocumentDTO {
    private Long documentId;
    private String documentTitle;
    private String documentUrl;
    private Integer documentTypeId;
    private Long ownerId;
}
