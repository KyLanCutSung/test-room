package com.example.springsocial.repository;

import com.example.springsocial.model.class_user.ClassUser;
import com.example.springsocial.model.class_user.ClassUserPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassUserRepository extends JpaRepository<ClassUser, ClassUserPK> {
    void deleteAllByClassId(Long classId);
    boolean existsByClassIdAndUserId(Long classId, Long userId);
}
