package com.example.springsocial.payload.auth_payload;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {
    private Long id;
    private String name;
    private String email;
    private String imageUrl;

}
