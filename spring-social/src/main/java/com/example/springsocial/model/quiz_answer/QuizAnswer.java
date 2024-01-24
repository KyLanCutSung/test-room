package com.example.springsocial.model.quiz_answer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "quiz_answer", schema = "web_room")
public class QuizAnswer {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "answer_id", nullable = false)
    private Long answerId;
    @Column(name = "quiz_id")
    private Long quizId;
    @Column(name = "answer")
    private String answer;
    @Column(name = "is_corrected")
    private Boolean isCorrected;
}
