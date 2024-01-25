package com.example.springsocial.service.impl;

import com.example.springsocial.exception.BadRequestException;
import com.example.springsocial.model.class_user.ClassUser;
import com.example.springsocial.model.class_user.ClassUserPK;
import com.example.springsocial.payload.class_payload.JoinClassDTO;
import com.example.springsocial.payload.class_user_payload.ApproveClassUserDTO;
import com.example.springsocial.repository.ClassUserRepository;
import com.example.springsocial.service.ClassUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ClassUserServiceImpl implements ClassUserService {
    private final ClassUserRepository classUserRepository;

    @Override
    public void createClassUserStatus(Long userId, Long classId) {
        ClassUser classUser = new ClassUser();
        if (classUserRepository.existsByClassIdAndUserId(classId, userId)) {
            throw new BadRequestException("Existed user in class!");
        }
        classUser.setClassId(classId);
        classUser.setUserId(userId);
        classUser.setIsAccepted(false);
        classUserRepository.save(classUser);
    }

    @Override
    public void acceptStudent(ApproveClassUserDTO approveClassUserDTO) {
        List<ClassUser> classUsers = new ArrayList<>();
        approveClassUserDTO.getClassUserDTOS().forEach(classUserDTO -> {
            ClassUser classUser = new ClassUser();
            BeanUtils.copyProperties(classUserDTO,classUser);
            classUser.setClassId(approveClassUserDTO.getClassId());
            classUsers.add(classUser);
        });
        classUserRepository.saveAll(classUsers);
    }

    @Override
    public void deleteStudentFromClass(Long classId) {
        classUserRepository.deleteAllByClassId(classId);
    }
}
