package com.example.springsocial.model.documents;

import com.example.springsocial.model.quiz.Quiz;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Entity
@Data
@Table(name = "documents", schema = "web_room")
@AllArgsConstructor
@NoArgsConstructor
public class Documents {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "document_id", nullable = false)
    private Long documentId;
    @Column(name = "document_title")
    private String documentTitle;
    @Column(name = "document_type_id")
    private Integer documentTypeId;
    @Column(name = "document_url")
    private String documentUrl;
    @Column(name = "owner_id")
    private Long ownerId;
    @Column(name = "created_date")
    private Date createdDate;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinColumn(name = "document_id")
    private List<Quiz> quizzes;
}
