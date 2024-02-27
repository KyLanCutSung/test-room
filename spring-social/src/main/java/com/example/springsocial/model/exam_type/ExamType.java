package com.example.springsocial.model.exam_type;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "exam_type", schema = "web_room")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExamType {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "exam_type_id", nullable = false)
    private Integer examTypeId;
    @Basic
    @Column(name = "exam_type")
    private String examType;
}
