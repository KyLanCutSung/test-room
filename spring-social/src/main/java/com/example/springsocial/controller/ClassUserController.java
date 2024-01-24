package com.example.springsocial.controller;

import com.example.springsocial.service.ClassUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/class-user")
@RequiredArgsConstructor
public class ClassUserController {
    private final ClassUserService classUserService;
}
