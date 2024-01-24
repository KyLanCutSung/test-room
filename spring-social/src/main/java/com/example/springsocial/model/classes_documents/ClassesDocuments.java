package com.example.springsocial.model.classes_documents;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "classes_documents", schema = "web_room")
@IdClass(ClassesDocumentsPK.class)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClassesDocuments {
    @Id
    @Column(name = "document_id", nullable = false)
    private long documentId;
    @Id
    @Column(name = "class_id", nullable = false)
    private long classId;

    @Column(name = "is_active")
    private Boolean isActive;

}
