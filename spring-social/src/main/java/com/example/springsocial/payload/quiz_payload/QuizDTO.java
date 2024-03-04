package com.example.springsocial.payload.quiz_payload;

import com.example.springsocial.payload.quiz_answer_payload.QuizAnswerDTO;
import lombok.Data;

import java.util.List;

@Data
public class QuizDTO {
    private Long documentId;
    private Long quizId;
    private String question;
    private List<QuizAnswerDTO> quizAnswerDTOS;
}
