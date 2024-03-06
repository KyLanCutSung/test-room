package com.example.springsocial.service.impl;

import com.example.springsocial.exception.ResourceNotFoundException;
import com.example.springsocial.model.classes.Classes;
import com.example.springsocial.model.document_exam.DocumentExam;
import com.example.springsocial.model.documents.Documents;
import com.example.springsocial.model.exams.Exams;
import com.example.springsocial.model.quiz.Quiz;
import com.example.springsocial.model.users.User;
import com.example.springsocial.payload.auth_payload.ApiResponse;
import com.example.springsocial.payload.exam_payload.ExamDTO;
import com.example.springsocial.payload.exam_result_payload.ExamResultDTO;
import com.example.springsocial.payload.quiz_answer_payload.QuizAnswerDTO;
import com.example.springsocial.payload.quiz_payload.QuizDTO;
import com.example.springsocial.repository.*;
import com.example.springsocial.service.ExamService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
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
    private final CustomQuizRepository customQuizRepository;
    private final QuizRepository quizRepository;

    @Override
    public ApiResponse createExam(ExamDTO dto) {
        Optional<User> user = userRepository.findById(dto.getOwnerId());
        Optional<Classes> classes = classRepository.findById(dto.getClassId());
        if (user.isPresent() && classes.isPresent()) {
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
            for (int i = 0; i < documentSize; i++) {
                Optional<DocumentExam> documentExamOptional = documentExamRepository.findByExamIdAndDocumentId(examProcess2.getExamId(), documents.get(i).getDocumentId());
                if (documentExamOptional.isPresent()) {
                    DocumentExam documentExam = documentExamOptional.get();
                    documentExam.setTotalQuestion(dto.getDocumentDTOS().get(i).getTotalQuestion());
                    documentExams.add(documentExam);
                }
            }
            documentExamRepository.saveAll(documentExams);
            return new ApiResponse(true, "Create exam successfully!");
        } else throw new ResourceNotFoundException("user", "id", dto.getOwnerId());
    }

    @Override
    public ApiResponse changeStatusExam(ExamDTO dto) {
        Optional<Exams> exam = examRepository.findByExamIdAndClassIdAndOwnerId(dto.getExamId(), dto.getClassId(), dto.getOwnerId());
        if (exam.isPresent()) {
            Exams exams = exam.get();
            exams.setStatus(dto.getStatus());
            exams.setClassId(dto.getClassId());
            exams.setOwnerId(dto.getOwnerId());
            examRepository.save(exams);
            return new ApiResponse(true,"Status of exam had change!");
        } else throw new ResourceNotFoundException("exam", "id", dto.getExamId());
    }

    @Override
    public Exams getExamById(Long examId) {
        Optional<Exams> examsOptional = examRepository.findById(examId);
        if (examsOptional.isPresent()) {
            return examsOptional.get();
        } else throw new ResourceNotFoundException("exam", "id", examId);
    }

    @Override
    public List<QuizDTO> doExam(ExamResultDTO examResultDTO) {
        Optional<Exams> examsOptional = examRepository.findById(examResultDTO.getExamId());
        if (examsOptional.isPresent()) {
            Exams exam = examsOptional.get();
            if (exam.getStatus().equals(true)) {
                List<DocumentExam> documentExams = documentExamRepository.findByExamId(examResultDTO.getExamId());
                if (!documentExams.isEmpty()) {
                    return getQuizDTOS(documentExams);
                }
            }
            else if (exam.getOwnerId().equals(examResultDTO.getUserId()) && exam.getStatus().equals(false)) {
                List<DocumentExam> documentExams = documentExamRepository.findByExamId(examResultDTO.getExamId());
                if (!documentExams.isEmpty()) {
                    return getQuizDTOS(documentExams);
                } else
                    throw new ResourceNotFoundException("User dont have permission", "user_id", examResultDTO.getUserId());
            }
        }
        throw new ResourceNotFoundException("exam not found", "exam_id", examResultDTO.getExamId());
    }

    private List<QuizDTO> getQuizDTOS(List<DocumentExam> documentExams) {
        List<Quiz> quizzesRandom = customQuizRepository.getQuizRandomWithAvailableParameters(documentExams);
        List<Long> quizIdTotal = new ArrayList<>();
        quizzesRandom.forEach(quiz -> quizIdTotal.add(quiz.getQuizId()));
        List<Quiz> quizzes = quizRepository.findAllById(quizIdTotal);
        List<QuizDTO> quizDTOS = new ArrayList<>();
        quizzes.forEach(quiz -> {
            List<QuizAnswerDTO> quizAnswerDTOS = new ArrayList<>();
            quiz.getQuizAnswers().forEach(quizAnswer -> {
                QuizAnswerDTO quizAnswerDTO = new QuizAnswerDTO();
                BeanUtils.copyProperties(quizAnswer, quizAnswerDTO);
                quizAnswerDTOS.add(quizAnswerDTO);
            });
            QuizDTO quizDTO = new QuizDTO();
            BeanUtils.copyProperties(quiz, quizDTO);
            quizDTO.setQuizAnswerDTOS(quizAnswerDTOS);
            quizDTOS.add(quizDTO);
        });
        Collections.shuffle(quizDTOS);
        return quizDTOS;
    }
}
