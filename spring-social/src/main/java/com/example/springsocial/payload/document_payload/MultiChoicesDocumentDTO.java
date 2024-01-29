package com.example.springsocial.payload.document_payload;

import com.example.springsocial.payload.quiz_payload.QuizDTO;
import lombok.Data;

import java.util.List;

@Data
public class MultiChoicesDocumentDTO {
    private Long documentId;
    private String documentTitle;
    private Long ownerId;
    private List<QuizDTO> quizDTOS;
}
