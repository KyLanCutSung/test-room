package com.example.springsocial.model.exams;

import com.example.springsocial.model.document_exam.DocumentExam;
import com.example.springsocial.model.documents.Documents;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "exams", schema = "web_room")
public class Exams implements Serializable {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "exam_id", nullable = false)
    private Long examId;
    @Basic
    @Column(name = "class_id")
    private Long classId;
    @Basic
    @Column(name = "owner_id")
    private Long ownerId;
    @Basic
    @Column(name = "exam_type_id")
    private Integer examTypeId;
    @Basic
    @Column(name = "status")
    private Boolean status;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "document_exam",
            joinColumns = @JoinColumn(name = "exam_id"),
            inverseJoinColumns = @JoinColumn(name = "document_id"))
    private List<Documents> documents;

}
