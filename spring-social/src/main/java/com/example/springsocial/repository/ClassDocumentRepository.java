package com.example.springsocial.repository;

import com.example.springsocial.model.classes_documents.ClassesDocuments;
import com.example.springsocial.model.classes_documents.ClassesDocumentsPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassDocumentRepository extends JpaRepository<ClassesDocuments, ClassesDocumentsPK> {
}
