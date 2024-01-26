package com.example.springsocial.service;

import com.example.springsocial.payload.exam_type_payload.ExamTypeDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ExamTypeService {
    List<ExamTypeDTO> getAll() throws Exception;
    ExamTypeDTO create(ExamTypeDTO examTypeDTO) throws Exception;
    void delete(Integer examTypeId) throws Exception;
}
