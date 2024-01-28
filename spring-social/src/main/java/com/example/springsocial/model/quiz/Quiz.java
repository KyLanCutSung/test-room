package com.example.springsocial.model.quiz;

import com.example.springsocial.model.quiz_answer.QuizAnswer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "quiz", schema = "web_room")
public class Quiz {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "quiz_id", nullable = false)
    private long quizId;
    @Column(name = "document_id")
    private Long documentId;
    @Column(name = "question")
    private String question;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    @JoinColumn(name = "quiz_id")
    private List<QuizAnswer> quizAnswers;
}
