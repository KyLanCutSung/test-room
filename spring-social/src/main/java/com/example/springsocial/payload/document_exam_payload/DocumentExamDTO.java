package com.example.springsocial.payload.document_exam_payload;

import com.example.springsocial.payload.document_payload.DocumentDTO;
import lombok.Data;

@Data
public class DocumentExamDTO {
    private Long documentId;
    private Long examId;
    private DocumentDTO documentDTO;
}
