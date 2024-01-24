package com.example.springsocial.model.exam_quiz;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "exam_quiz", schema = "web_room")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExamQuiz {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "exam_quiz_id", nullable = false)
    private Long examQuizId;
    @Column(name = "exam_id")
    private Long examId;
    @Column(name = "answer_id")
    private Long answerId;
    @Column(name = "answer")
    private Integer answer;

}
