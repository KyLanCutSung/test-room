package com.example.springsocial.service.impl;

import com.example.springsocial.exception.BadRequestException;
import com.example.springsocial.exception.ResourceNotFoundException;
import com.example.springsocial.model.classes.Classes;
import com.example.springsocial.payload.class_payload.ClassDTO;
import com.example.springsocial.payload.class_payload.JoinClassDTO;
import com.example.springsocial.payload.class_user_payload.ApproveClassUserDTO;
import com.example.springsocial.repository.ClassRepository;
import com.example.springsocial.service.ClassService;
import com.example.springsocial.service.ClassUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.BeanUtils;
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

    @Override
    public List<ClassDTO> findAll() {
        List<Classes> classList = classRepository.findAll();
        List<ClassDTO> classDTOS = new ArrayList<>();
        classList.forEach(result -> {
            ClassDTO classDTO = new ClassDTO();
            BeanUtils.copyProperties(result,classDTO);
            classDTOS.add(classDTO);
        });
        return classDTOS;
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
    public boolean delete(Long classId, Long userId) {
        log.debug("Delete by classId and userId Class {},{}",classId, userId);
        Optional<Classes> classesOptional = classRepository.findByClassIdAndOwnerId(classId,userId);
        if(classesOptional.isPresent()){
            try {
                classUserService.deleteStudentFromClass(classId);
                classRepository.deleteById(classesOptional.get().getClassId());
            } catch (Exception e) {
                throw new ResourceNotFoundException("Class","class_id",classId);
            }
            return true;
        }
        return false;
    }
    @Override
    public void joinClass(JoinClassDTO joinClassDTO) {
        Optional<Classes> result = classRepository.findByClassCode(joinClassDTO.getClassCode());
        result.ifPresent(classes -> {
            try {
                classUserService.createClassUserStatus(joinClassDTO.getUserId(), classes.getClassId());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }
    @Override
    @Transactional
    public void classApproval(ApproveClassUserDTO dto) throws Exception {
        Optional<Classes> classes = classRepository.findByClassIdAndOwnerId(dto.getClassId(), dto.getOwnerId());
        if (classes.isPresent()) {
            classUserService.acceptStudent(dto);
        } else {
            throw new BadRequestException("Cannot approve!");
        }
    }
}
