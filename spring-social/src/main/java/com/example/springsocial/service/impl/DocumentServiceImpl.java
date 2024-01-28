package com.example.springsocial.service.impl;

import com.example.springsocial.model.documents.Documents;
import com.example.springsocial.model.quiz.Quiz;
import com.example.springsocial.model.quiz_answer.QuizAnswer;
import com.example.springsocial.payload.document_payload.DocumentDTO;
import com.example.springsocial.repository.DocumentRepository;
import com.example.springsocial.service.DocumentService;
import com.example.springsocial.service.ExcelService;
import com.example.springsocial.service.QuizAnswerService;
import com.example.springsocial.service.QuizService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.Instant;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class DocumentServiceImpl implements DocumentService {
    private final DocumentRepository documentRepository;
    private final ExcelService excelService;
    private final ModelMapper modelMapper;


    @Override
    public DocumentDTO saveDocumentAndQuiz(MultipartFile file, DocumentDTO documentDTO) throws IOException {
        Documents documents = modelMapper.map(documentDTO, Documents.class);
        if (excelService.compareMergeCell(file.getInputStream())){
            documents.setCreatedDate(Date.from(Instant.now()));
            documents =  documentRepository.save(documents);
            excelService.excelToQuizzes(file.getInputStream(),documents.getDocumentId());
        } else {
            throw new RuntimeException("Excel doesn't right format!");
        }
        return modelMapper.map(documents, DocumentDTO.class);
    }
}
