package com.example.springsocial.model.exams;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "exams", schema = "web_room")
public class Exams {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "exam_id", nullable = false)
    private Long examId;
    @Basic
    @Column(name = "class_id")
    private Long classId;
    @Basic
    @Column(name = "owner_id")
    private Long userId;
    @Basic
    @Column(name = "exam_type_id")
    private Integer examTypeId;
    @Basic
    @Column(name = "status")
    private boolean status;
}
