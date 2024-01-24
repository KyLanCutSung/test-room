package com.example.springsocial.service.impl;

import com.example.springsocial.repository.ClassUserRepository;
import com.example.springsocial.service.ClassUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ClassUserServiceImpl implements ClassUserService {
    private final ClassUserRepository classUserRepository;
}
