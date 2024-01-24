package com.example.springsocial.repository;

import com.example.springsocial.model.document_types.DocumentTypes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentTypeRepository extends JpaRepository<DocumentTypes,Integer> {
}
