package com.example.springsocial.service.impl;

import com.example.springsocial.model.documents.Documents;
import com.example.springsocial.model.quiz.Quiz;
import com.example.springsocial.model.quiz_answer.QuizAnswer;
import com.example.springsocial.payload.document_payload.DocumentDTO;
import com.example.springsocial.payload.document_payload.MultiChoicesDocumentDTO;
import com.example.springsocial.payload.quiz_answer_payload.QuizAnswerDTO;
import com.example.springsocial.payload.quiz_payload.QuizDTO;
import com.example.springsocial.repository.DocumentRepository;
import com.example.springsocial.service.DocumentService;
import com.example.springsocial.service.ExcelService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class DocumentServiceImpl implements DocumentService {
    private final DocumentRepository documentRepository;
    private final ExcelService excelService;
    private final ModelMapper modelMapper;

    @Override
    public DocumentDTO saveDocument(MultipartFile file, DocumentDTO documentDTO) throws IOException {
        Documents documents = modelMapper.map(documentDTO, Documents.class);
            if (excelService.compareMergeCell(file.getInputStream())){
                documents.setCreatedDate(Date.from(Instant.now()));
                excelService.excelToQuizzes(file.getInputStream(),documents);
            } else {
                throw new RuntimeException("Excel doesn't right format!");
            }
            return modelMapper.map(documents, DocumentDTO.class);
    }

    @Override
    public DocumentDTO saveDocument(DocumentDTO documentDTO) {
        Documents documents = modelMapper.map(documentDTO, Documents.class);
        documents = documentRepository.save(documents);
        return modelMapper.map(documents,DocumentDTO.class);
    }

    @Override
    public MultiChoicesDocumentDTO findMultiChoicesDocumentById(Long documentId){
        MultiChoicesDocumentDTO multiChoicesDocumentDTO = new MultiChoicesDocumentDTO();
        List<QuizDTO> quizDTOS = new ArrayList<>();
        Optional<Documents> documentOptional = documentRepository.findById(documentId);
        if (documentOptional.isPresent()){
            Documents documents = documentOptional.get();
            documents.getQuizzes().forEach(quiz -> {
                QuizDTO quizDTO = new QuizDTO();
                List<QuizAnswerDTO> quizAnswerDTOS = new ArrayList<>();
                quiz.getQuizAnswers().forEach(quizAnswer -> {
                    QuizAnswerDTO quizAnswerDTO = new QuizAnswerDTO();
                    BeanUtils.copyProperties(quizAnswer,quizAnswerDTO);
                    quizAnswerDTOS.add(quizAnswerDTO);
                });
                BeanUtils.copyProperties(quiz,quizDTO);
                quizDTO.setQuizAnswerDTOS(quizAnswerDTOS);
                quizDTOS.add(quizDTO);
            });
            BeanUtils.copyProperties(documents, multiChoicesDocumentDTO);
            multiChoicesDocumentDTO.setQuizDTOS(quizDTOS);
        }
        return multiChoicesDocumentDTO;
    }
}
