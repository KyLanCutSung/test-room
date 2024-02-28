package com.example.springsocial.payload.exam_payload;

import lombok.Data;

@Data
public class ExamDTO {
    private Long examId;
    private Long classId;
    private Long ownerId;
    private Boolean status;
    private Integer examTypeId;
}
