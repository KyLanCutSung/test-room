package com.example.springsocial.service.impl;

import com.example.springsocial.exception.ResourceNotFoundException;
import com.example.springsocial.model.classes.Classes;
import com.example.springsocial.payload.class_payload.ClassDTO;
import com.example.springsocial.repository.ClassRepository;
import com.example.springsocial.service.ClassService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.Date;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ClassServiceImpl implements ClassService {
    private final ClassRepository classRepository;

    @Override
    public List<Classes> findAll() {
        return classRepository.findAll();
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
    public boolean delete(Long classId, Long userId) {
        log.debug("Delete by classId and userId Class {},{}",classId, userId);
        Optional<Classes> classesOptional = classRepository.findByClassIdAndOwnerId(classId,userId);
        if(classesOptional.isPresent()){
            try {
                classRepository.delete(classesOptional.get());
            } catch (Exception e) {
                throw new ResourceNotFoundException("Class","class_id",classId);
            }
            return true;
        }
        return false;
    }
}
