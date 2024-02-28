package com.example.springsocial.model.exam_result;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

@Entity
@Table(name = "exam_result", schema = "web_room")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExamResult {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "exam_result_id")
    private Long examResultId;
    @Basic
    @Column(name = "exam_id")
    private Long examId;
    @Basic
    @Column(name = "user_id")
    private Long userId;
    @Basic
    @Column(name = "started_time")
    private Date startedTime;
    @Basic
    @Column(name = "ended_time")
    private Date endedTime;
}
