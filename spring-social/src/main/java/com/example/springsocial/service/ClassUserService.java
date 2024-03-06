package com.example.springsocial.service;

import com.example.springsocial.payload.class_user_payload.ApproveClassUserDTO;

public interface ClassUserService {
    void createClassUserStatus(Long userId, Long classId);
    void acceptStudent(ApproveClassUserDTO approveClassUserDTO) throws Exception;
    void deleteStudentFromClass(Long classId);
}
