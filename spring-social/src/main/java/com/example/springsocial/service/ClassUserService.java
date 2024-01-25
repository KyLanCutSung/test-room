package com.example.springsocial.service;

import com.example.springsocial.model.class_user.ClassUser;
import com.example.springsocial.payload.class_payload.JoinClassDTO;
import com.example.springsocial.payload.class_user_payload.ApproveClassUserDTO;

public interface ClassUserService {
    void createClassUserStatus(Long userId, Long classId) throws Exception;
    void acceptStudent(ApproveClassUserDTO approveClassUserDTO) throws Exception;
    void deleteStudentFromClass(Long classId) throws Exception;
}
