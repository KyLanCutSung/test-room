package com.example.springsocial.controller;

import com.example.springsocial.model.exams.Exams;
import com.example.springsocial.payload.auth_payload.ApiResponse;
import com.example.springsocial.payload.exam_payload.ExamDTO;
import com.example.springsocial.payload.exam_result_payload.ExamResultDTO;
import com.example.springsocial.payload.quiz_payload.QuizDTO;
import com.example.springsocial.service.ExamService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/exam")
@RequiredArgsConstructor
public class ExamController {
    private final ExamService examService;

    @PostMapping("/createExam")
    @PreAuthorize("hasAnyRole('TEACHER','STUDENT')")
    public ResponseEntity<ApiResponse> createExam(@RequestBody ExamDTO dto) {
        return new ResponseEntity<>(examService.createExam(dto),HttpStatus.OK);
    }

    @PostMapping("/changeStatus")
    @PreAuthorize("hasRole('TEACHER')")
    public ResponseEntity<ApiResponse> changeStatusExam(@RequestBody ExamDTO dto) {

        return new ResponseEntity<>(examService.changeStatusExam(dto),HttpStatus.OK);
    }

    @GetMapping("/getExam/{examId}")
    @PreAuthorize("hasRole('TEACHER')")
    public ResponseEntity<Exams> getExam(@PathVariable("examId") Long examId){
        return new ResponseEntity<>(examService.getExamById(examId), HttpStatus.OK);
    }

    @PostMapping("doExam")
    @PreAuthorize("hasRole('STUDENT')")
    public ResponseEntity<List<QuizDTO>> doExam(@RequestBody ExamResultDTO examResultDTO) {
        return new ResponseEntity<>(examService.doExam(examResultDTO),HttpStatus.OK);
    }
}
