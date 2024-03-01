package com.example.springsocial.payload.exam_payload;

import com.example.springsocial.payload.document_payload.DocumentDTO;
import lombok.Data;

import java.util.List;

@Data
public class ExamDTO {
    private Long examId;
    private Long classId;
    private Long ownerId;
    private Boolean status;
    private Integer examTypeId;
    private List<DocumentDTO> documentDTOS;
}
