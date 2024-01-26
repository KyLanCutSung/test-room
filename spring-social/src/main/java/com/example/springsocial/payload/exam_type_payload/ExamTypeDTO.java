package com.example.springsocial.payload.exam_type_payload;

import lombok.Data;

@Data
public class ExamTypeDTO {
    private Integer examTypeId;
    private String examType;
    private Integer submissionTime;
}
