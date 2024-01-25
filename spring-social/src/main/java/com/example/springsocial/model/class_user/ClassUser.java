package com.example.springsocial.model.class_user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "class_user", schema = "web_room")
@IdClass(ClassUserPK.class)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClassUser {
    @Id
    @Column(name = "class_id", nullable = false)
    private long classId;
    @Id
    @Column(name = "user_id", nullable = false)
    private long userId;
    @Basic
    @Column(name = "is_accepted")
    private Boolean isAccepted;
}
