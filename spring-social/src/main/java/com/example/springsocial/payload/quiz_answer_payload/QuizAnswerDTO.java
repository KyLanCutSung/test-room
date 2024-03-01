package com.example.springsocial.payload.quiz_answer_payload;

import lombok.Data;

@Data
public class QuizAnswerDTO {
    private Long answerId;
    private String answer;
    private Boolean isCorrected;
}
