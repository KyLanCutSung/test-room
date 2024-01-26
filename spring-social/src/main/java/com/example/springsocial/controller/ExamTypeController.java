package com.example.springsocial.controller;

import com.example.springsocial.payload.Response;
import com.example.springsocial.payload.exam_type_payload.ExamTypeDTO;
import com.example.springsocial.service.ExamTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/exam-type")
@RequiredArgsConstructor
public class ExamTypeController {
    private final ExamTypeService examTypeService;
    @GetMapping("/getAll")
    public Response<List<ExamTypeDTO>> getAll() throws Exception {
        return Response.ok(examTypeService.getAll());
    }
}
