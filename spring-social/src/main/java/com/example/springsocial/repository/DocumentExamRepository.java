package com.example.springsocial.repository;

import com.example.springsocial.model.document_exam.DocumentExam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DocumentExamRepository extends JpaRepository<DocumentExam,Long> {
    Optional<DocumentExam> findByExamIdAndDocumentId(Long examId, Long documentId);
}
