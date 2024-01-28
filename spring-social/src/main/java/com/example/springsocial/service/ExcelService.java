package com.example.springsocial.service;

import com.example.springsocial.model.documents.Documents;
import com.example.springsocial.model.quiz.Quiz;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public interface ExcelService {
    boolean compareMergeCell(InputStream inputStream) throws IOException;
    void excelToQuizzes(InputStream inputStream, Long documentId) throws IOException;
}
