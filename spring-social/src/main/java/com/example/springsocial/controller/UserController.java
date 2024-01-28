package com.example.springsocial.controller;

import com.example.springsocial.exception.ResourceNotFoundException;
import com.example.springsocial.model.users.User;
import com.example.springsocial.payload.auth_payload.UserDTO;
import com.example.springsocial.repository.UserRepository;
import com.example.springsocial.security.CurrentUser;
import com.example.springsocial.security.CustomUserDetailsService;
import com.example.springsocial.security.UserPrincipal;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final CustomUserDetailsService customUserDetailsService;

    @GetMapping("/me")
    @PreAuthorize("hasAnyRole('STUDENT','TEACHER','ADMIN')")
    public UserDTO getCurrentUser(@CurrentUser UserPrincipal userPrincipal) {
        return customUserDetailsService.findById(userPrincipal.getId());
    }
}
