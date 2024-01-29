package com.example.springsocial.service;

import com.example.springsocial.payload.class_document_payload.StatusDocumentDTO;
import com.example.springsocial.payload.class_payload.ClassDTO;
import com.example.springsocial.payload.class_payload.JoinClassDTO;
import com.example.springsocial.payload.class_user_payload.ApproveClassUserDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ClassService {
    Page<ClassDTO> findAll(Pageable pageable) throws Exception;
    ClassDTO create(ClassDTO classDTO) throws Exception;
    boolean delete(Long classId, Long userId) throws Exception;
    void joinClass(JoinClassDTO joinClassDTO) throws Exception;
    void classApproval(ApproveClassUserDTO dto) throws Exception;
    Page<ClassDTO> findByOwnerId(Long userId, Pageable pageable);
    boolean activeDocument(StatusDocumentDTO statusDocumentDTO);
    void deactivateDocument(StatusDocumentDTO statusDocumentDTO);
}
