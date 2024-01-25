package com.example.springsocial.service;

import com.example.springsocial.model.classes.Classes;
import com.example.springsocial.payload.class_payload.ClassDTO;
import com.example.springsocial.payload.class_payload.JoinClassDTO;
import com.example.springsocial.payload.class_user_payload.ApproveClassUserDTO;

import java.util.List;

public interface ClassService {
    List<ClassDTO> findAll() throws Exception;
    ClassDTO create(ClassDTO classDTO) throws Exception;
    boolean delete(Long classId, Long userId) throws Exception;
    void joinClass(JoinClassDTO joinClassDTO) throws Exception;
    void classApproval(ApproveClassUserDTO dto) throws Exception;
}
