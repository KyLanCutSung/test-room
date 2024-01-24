package com.example.springsocial.model.class_user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClassUserPK implements Serializable {
    private long classId;
    private long userId;
}
