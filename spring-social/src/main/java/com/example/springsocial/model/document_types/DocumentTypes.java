package com.example.springsocial.model.document_types;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "document_types", schema = "web_room")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DocumentTypes {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "document_type_id", nullable = false)
    private Integer documentTypeId;
    @Column(name = "document_type")
    private String documentType;

}
