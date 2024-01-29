package com.example.springsocial.repository;

import com.example.springsocial.model.documents.Documents;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DocumentRepository extends JpaRepository<Documents,Long> {
    Optional<Documents> findByDocumentIdAndOwnerId(Long documentId, Long userId);
}
