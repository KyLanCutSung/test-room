package com.example.springsocial.payload.auth_payload;

import com.example.springsocial.model.users.AuthProvider;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Getter
@Setter
public class UserDTO {
    private Long id;
    private String name;
    private String email;
    private String imageUrl;
    private Boolean emailVerified;
    @Enumerated(EnumType.STRING)
    private AuthProvider provider;
    private String providerId;
}
