package com.example.springsocial.service.impl;

import com.example.springsocial.exception.ResourceNotFoundException;
import com.example.springsocial.model.classes.Classes;
import com.example.springsocial.model.document_exam.DocumentExam;
import com.example.springsocial.model.documents.Documents;
import com.example.springsocial.model.exams.Exams;
import com.example.springsocial.model.users.User;
import com.example.springsocial.payload.exam_payload.ExamDTO;
import com.example.springsocial.repository.ClassRepository;
import com.example.springsocial.repository.DocumentExamRepository;
import com.example.springsocial.repository.ExamRepository;
import com.example.springsocial.repository.UserRepository;
import com.example.springsocial.service.ExamService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class ExamServiceImpl implements ExamService {
    private final ExamRepository examRepository;
    private final UserRepository userRepository;
    private final ClassRepository classRepository;
    private final DocumentExamRepository documentExamRepository;

    @Override
    public void createExam(ExamDTO dto) {
        Optional<User> user = userRepository.findById(dto.getOwnerId());
        Optional<Classes> classes = classRepository.findById(dto.getClassId());
        if (user.isPresent() && classes.isPresent()){
            Exams examProcess1 = new Exams();
            List<Documents> documents = new ArrayList<>();
            dto.getDocumentDTOS().forEach(documentDTO -> {
                Documents document = new Documents();
                BeanUtils.copyProperties(documentDTO, document);
                documents.add(document);
            });
            BeanUtils.copyProperties(dto, examProcess1);
            examProcess1.setDocuments(documents);
            examProcess1.setStatus(false); // Only owner can access
            Exams examProcess2 = examRepository.save(examProcess1);
            int documentSize = dto.getDocumentDTOS().size();
            List<DocumentExam> documentExams = new ArrayList<>();
            for (int i = 0;i < documentSize;i++){
                Optional<DocumentExam> documentExamOptional = documentExamRepository.findByExamIdAndDocumentId(examProcess2.getExamId(), documents.get(i).getDocumentId());
                if (documentExamOptional.isPresent()){
                    DocumentExam documentExam = documentExamOptional.get();
                    documentExam.setTotalQuestion(dto.getDocumentDTOS().get(i).getTotalQuestion());
                    documentExams.add(documentExam);
                }
            }
            documentExamRepository.saveAll(documentExams);
        } else throw new ResourceNotFoundException("user","id",dto.getOwnerId());
    }

    @Override
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

    @Override
    public Exams getExamById(Long examId) {
        Optional<Exams> examsOptional = examRepository.findById(examId);
        if (examsOptional.isPresent()){
            return examsOptional.get();
        } else throw new ResourceNotFoundException("exam", "id", examId);
    }
}
