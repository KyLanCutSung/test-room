package com.example.springsocial.controller;

import com.example.springsocial.model.exams.Exams;
import com.example.springsocial.payload.Response;
import com.example.springsocial.payload.exam_payload.ExamDTO;
import com.example.springsocial.service.ExamService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/exam")
@RequiredArgsConstructor
public class ExamController {
    private final ExamService examService;

    @PostMapping("/createExam")
    @PreAuthorize("hasAnyRole('TEACHER','STUDENT')")
    public void createExam(@RequestBody ExamDTO dto) {
        examService.createExam(dto);
    }

    @PostMapping("/changeStatus")
    @PreAuthorize("hasRole('TEACHER')")
    public void changeStatusExam(@RequestBody ExamDTO dto) {
        examService.changeStatusExam(dto);
    }

    @GetMapping("/getExam/{examId}")
    @PreAuthorize("hasRole('TEACHER')")
    public Response<Exams> getExam(@PathVariable("examId") Long examId){
        return Response.ok(examService.getExamById(examId));
    }
}
