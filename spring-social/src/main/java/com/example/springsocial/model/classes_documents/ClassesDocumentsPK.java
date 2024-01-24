package com.example.springsocial.model.classes_documents;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClassesDocumentsPK implements Serializable {
    private long documentId;
    private long classId;
}
