package com.example.springsocial.payload.class_user_payload;

import lombok.Data;

import java.util.List;

@Data
public class ApproveClassUserDTO {
    private Long ownerId;
    private Long classId;
    private List<ClassUserDTO> classUserDTOS;
}
