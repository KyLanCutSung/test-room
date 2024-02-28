package com.example.springsocial.service.impl;

import com.example.springsocial.exception.ResourceNotFoundException;
import com.example.springsocial.model.classes.Classes;
import com.example.springsocial.model.exams.Exams;
import com.example.springsocial.model.users.User;
import com.example.springsocial.payload.exam_payload.ExamDTO;
import com.example.springsocial.repository.ClassRepository;
import com.example.springsocial.repository.ExamRepository;
import com.example.springsocial.repository.UserRepository;
import com.example.springsocial.service.ExamService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class ExamServiceImpl implements ExamService {
    private final ExamRepository examRepository;
    private final UserRepository userRepository;
    private final ClassRepository classRepository;

    @Override
    public void createExam(ExamDTO dto) {
        Optional<User> user = userRepository.findById(dto.getOwnerId());
        Optional<Classes> classes = classRepository.findById(dto.getClassId());
        if (user.isPresent() && classes.isPresent()){
            Exams exam = new Exams();
            BeanUtils.copyProperties(dto, exam);
            exam.setStatus(false); // Only owner can access
            examRepository.save(exam);
        } else throw new ResourceNotFoundException("user","id",dto.getOwnerId());
    }

    public void changeStatusExam(ExamDTO dto) {
        Optional<Exams> exam = examRepository.findByExamIdAndClassIdAndOwnerId(dto.getExamId(), dto.getClassId(), dto.getOwnerId());
        if (exam.isPresent()) {
            Exams exams = exam.get();
            exams.setStatus(dto.getStatus());
            exams.setClassId(dto.getClassId());
            exams.setOwnerId(dto.getOwnerId());
            examRepository.save(exams);
        } else throw new ResourceNotFoundException("exam", "id",dto.getExamId());
    }
}
