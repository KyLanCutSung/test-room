package com.example.springsocial.repository;

import com.example.springsocial.payload.class_payload.ActiveDocumentInClassDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class CustomClassDocumentRepository {
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public List<ActiveDocumentInClassDTO> findActiveDocumentInClass(Long classId) {
        String sql = "select c.class_name,d.document_id,d.document_title\n" +
                " from classes c\n" +
                " join web_room.classes_documents cd on c.class_id = cd.class_id\n" +
                " join web_room.documents d on cd.document_id = d.document_id\n" +
                " where cd.is_active = true and c.class_id = :classId";

        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("classId",classId);
        return namedParameterJdbcTemplate.query(sql,parameterSource,new BeanPropertyRowMapper<>(ActiveDocumentInClassDTO.class));
    }
}
