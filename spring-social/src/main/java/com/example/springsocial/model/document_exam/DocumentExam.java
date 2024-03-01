package com.example.springsocial.model.document_exam;

import com.example.springsocial.model.documents.Documents;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "document_exam", schema = "web_room")
public class DocumentExam {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Long id;
    @Basic
    @Column(name = "document_id", insertable = false, updatable = false)
    private Long documentId;
    @Basic
    @Column(name = "exam_id")
    private Long examId;

    @Basic
    @Column(name = "total_question")
    private Long totalQuestion;
}
