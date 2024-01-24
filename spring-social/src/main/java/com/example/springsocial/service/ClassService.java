package com.example.springsocial.service;

import com.example.springsocial.model.classes.Classes;
import com.example.springsocial.payload.class_payload.ClassDTO;

import java.util.List;

public interface ClassService {
    List<Classes> findAll() throws Exception;
    ClassDTO create(ClassDTO classDTO) throws Exception;
    boolean delete(Long classId, Long userId) throws Exception;
}
