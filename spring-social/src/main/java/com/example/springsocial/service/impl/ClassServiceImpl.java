package com.example.springsocial.service.impl;

import com.example.springsocial.exception.BadRequestException;
import com.example.springsocial.exception.ResourceNotFoundException;
import com.example.springsocial.model.classes.Classes;
import com.example.springsocial.model.classes_documents.ClassesDocuments;
import com.example.springsocial.payload.auth_payload.ApiResponse;
import com.example.springsocial.payload.class_document_payload.ClassDocumentDTO;
import com.example.springsocial.payload.class_payload.ActiveDocumentInClassDTO;
import com.example.springsocial.payload.class_payload.ClassDTO;
import com.example.springsocial.payload.class_payload.JoinClassDTO;
import com.example.springsocial.payload.class_user_payload.ApproveClassUserDTO;
import com.example.springsocial.repository.CustomClassDocumentRepository;
import com.example.springsocial.repository.ClassDocumentRepository;
import com.example.springsocial.repository.ClassRepository;
import com.example.springsocial.service.ClassService;
import com.example.springsocial.service.ClassUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.utility.RandomString;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ClassServiceImpl implements ClassService {
    private final ClassUserService classUserService;
    private final ClassRepository classRepository;
    private final ModelMapper modelMapper;
    private final ClassDocumentRepository classDocumentRepository;
    private final CustomClassDocumentRepository customClassDocumentRepository;
    @Override
    public Page<ClassDTO> findAll(Pageable pageable) {
         return classRepository.findAll(pageable)
                 .map(classes -> modelMapper.map(classes,ClassDTO.class));
    }

    @Override
    public Page<ClassDTO> findByOwnerId(Long userId, Pageable pageable) {
        return classRepository.findAllByOwnerId(userId,pageable)
                .map(classes -> modelMapper.map(classes,ClassDTO.class));

    }
    @Override
    public ClassDTO create(ClassDTO classDTO) {
        log.debug("Save new class {}",classDTO);
        Classes classes = new Classes();
        BeanUtils.copyProperties(classDTO,classes);
        classes.setCreatedDate(Date.from(Instant.now()));
        classes.setClassCode(RandomString.make(10));
        classes = classRepository.save(classes);
        BeanUtils.copyProperties(classes,classDTO);
        return classDTO;
    }

    @Override
    @Transactional
    public ApiResponse delete(Long classId, Long userId) {
        log.debug("Delete by classId and userId Class {},{}",classId, userId);
        Optional<Classes> classesOptional = classRepository.findByClassIdAndOwnerId(classId,userId);
        if(classesOptional.isPresent()) {
                classUserService.deleteStudentFromClass(classId);
                classRepository.delete(classesOptional.get());
                return new ApiResponse(true, "Delete Complete!");
            }
        throw new ResourceNotFoundException("Class", "class_id", classId);
    }
    @Override
    public ApiResponse joinClass(JoinClassDTO joinClassDTO) {
        Optional<Classes> result = classRepository.findByClassCode(joinClassDTO.getClassCode());
        if (result.isPresent()){
            Classes classes = result.get();
            classUserService.createClassUserStatus(joinClassDTO.getUserId(), classes.getClassId());
            return new ApiResponse(true,"Wait for accepted!");
        }
        else throw new ResourceNotFoundException("Class","code",joinClassDTO.getClassCode());
    }

    @Override
    @Transactional
    public ApiResponse classApproval(ApproveClassUserDTO dto) throws Exception {
        Optional<Classes> classes = classRepository.findByClassIdAndOwnerId(dto.getClassId(), dto.getOwnerId());
        if (classes.isPresent()) {
            classUserService.acceptStudent(dto);
            return new ApiResponse(true,"Complete!");
        } else throw new BadRequestException("Cannot approve!");
    }

    @Override
    public ApiResponse activeDocument(List<ClassDocumentDTO> classDocumentDTOS) {
            List<ClassesDocuments> classesDocuments = new ArrayList<>();
            classDocumentDTOS.forEach(classDocumentDTO -> {
                ClassesDocuments classDocument = new ClassesDocuments();
                BeanUtils.copyProperties(classDocumentDTO,classDocument);
                classDocument.setIsActive(true);
                classesDocuments.add(classDocument);
            });
            classDocumentRepository.saveAll(classesDocuments);
            return new ApiResponse(true, "Complete!");
    }

    @Override
    public ApiResponse deactivateDocument(List<ClassDocumentDTO> classDocumentDTOS) {
            List<ClassesDocuments> classesDocuments = new ArrayList<>();
            classDocumentDTOS.forEach(classDocumentDTO -> {
                ClassesDocuments classDocument = new ClassesDocuments();
                BeanUtils.copyProperties(classDocumentDTO,classDocument);
                classDocument.setIsActive(false);
                classesDocuments.add(classDocument);
            });
            classDocumentRepository.saveAll(classesDocuments);
        return new ApiResponse(true, "Complete!");
    }

    @Override
    public List<ActiveDocumentInClassDTO> findActiveDocumentInClass(Long classId) {
        return customClassDocumentRepository.findActiveDocumentInClass(classId);
    }
}
