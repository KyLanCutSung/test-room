package com.example.springsocial.controller;

import com.example.springsocial.model.classes.Classes;
import com.example.springsocial.payload.Response;
import com.example.springsocial.payload.class_payload.ClassDTO;
import com.example.springsocial.service.ClassService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/class")
@RequiredArgsConstructor
public class ClassController {
    private final ClassService classService;

    @GetMapping("/getAll")
    public Response<List<Classes>> findAll() throws Exception {
        return Response.ok(classService.findAll());
    }

    @PostMapping("/create")
    public Response<ClassDTO> create(@RequestBody ClassDTO classDTO) throws Exception {
        return Response.created(classService.create(classDTO));
    }

    @DeleteMapping("/delete/{classId}")
    public Response<Object> deleteClass(@PathVariable(value = "classId", required = false) Long classId,
                                        @RequestParam(value = "userId", required = false) Long userId) throws Exception {
        boolean result = classService.delete(classId,userId);
        if (result) {
            return Response.deleted().setMessage("api.class.delete.success","Delete record successfully!");
        }
        return Response.bad().setMessage("api.class.delete.fail","Fail delete record!");
    }
}
