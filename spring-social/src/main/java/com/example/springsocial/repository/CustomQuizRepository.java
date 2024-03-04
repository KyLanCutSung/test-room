package com.example.springsocial.repository;

import com.example.springsocial.model.document_exam.DocumentExam;
import com.example.springsocial.model.quiz.Quiz;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class CustomQuizRepository {
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public List<Quiz> getQuizRandomWithAvailableParameters(List<DocumentExam> documentExams){
        StringBuilder sSQL = new StringBuilder();
        sSQL.append("select quiz_id from (");
        DocumentExam documentExam = documentExams.get(0);
        if (documentExams.size() == 1){
            sSQL.append("(select quiz_id from quiz where document_id = ")
                    .append(documentExam.getDocumentId())
                    .append(" order by rand() limit ")
                    .append(documentExam.getTotalQuestion())
                    .append(")) as random_rows");
        } else {
            sSQL.append("(select quiz_id from quiz where document_id = ")
                    .append(documentExam.getDocumentId())
                    .append(" order by rand() limit ")
                    .append(documentExam.getTotalQuestion())
                    .append(")");
            for (int i = 1;i<=documentExams.size()-1;i++){
                sSQL.append("union all (select quiz_id from quiz where document_id = ")
                        .append(documentExams.get(i).getDocumentId())
                        .append(" order by rand() limit ")
                        .append(documentExams.get(i).getTotalQuestion())
                        .append(")");
            }
            sSQL.append(") as random_rows order by rand();");
        }
        String sql = sSQL.toString();
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        return namedParameterJdbcTemplate.query(sql, parameterSource, new BeanPropertyRowMapper<>(Quiz.class));
    }
}
