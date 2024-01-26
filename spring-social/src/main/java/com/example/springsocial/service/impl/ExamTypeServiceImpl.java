package com.example.springsocial.service.impl;

import com.example.springsocial.model.exam_type.ExamType;
import com.example.springsocial.payload.exam_type_payload.ExamTypeDTO;
import com.example.springsocial.repository.ExamTypeRepository;
import com.example.springsocial.service.ExamTypeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class ExamTypeServiceImpl implements ExamTypeService {
    private final ExamTypeRepository examTypeRepository;
    private final ModelMapper modelMapper;
    @Override
    public List<ExamTypeDTO> getAll() {
        List<ExamType> examTypes = examTypeRepository.findAll();
        return examTypes.stream().map(examType -> modelMapper.map(examType, ExamTypeDTO.class)).collect(Collectors.toList());
    }

    @Override
    public ExamTypeDTO create(ExamTypeDTO examTypeDTO) {
        ExamType examType = examTypeRepository.save(modelMapper.map(examTypeDTO, ExamType.class));
        return modelMapper.map(examType, ExamTypeDTO.class);
    }

    @Override
    public void delete(Integer examTypeId) {
        examTypeRepository.deleteById(examTypeId);
    }

}
