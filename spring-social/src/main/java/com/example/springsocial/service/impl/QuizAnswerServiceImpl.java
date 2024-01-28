package com.example.springsocial.service.impl;

import com.example.springsocial.model.quiz_answer.QuizAnswer;
import com.example.springsocial.repository.QuizAnswerRepository;
import com.example.springsocial.service.QuizAnswerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class QuizAnswerServiceImpl implements QuizAnswerService {
    private final QuizAnswerRepository quizAnswerRepository;

    @Override
    public void saveAll(List<QuizAnswer> quizAnswers){
        quizAnswerRepository.saveAll(quizAnswers);
    }
}
