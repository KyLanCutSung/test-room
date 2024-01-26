package com.example.springsocial.controller;

import com.example.springsocial.model.classes.Classes;
import com.example.springsocial.payload.Response;
import com.example.springsocial.payload.class_payload.ClassDTO;
import com.example.springsocial.payload.class_payload.JoinClassDTO;
import com.example.springsocial.payload.class_user_payload.ApproveClassUserDTO;
import com.example.springsocial.service.ClassService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/class")
@RequiredArgsConstructor
public class ClassController {
    private final ClassService classService;
    @GetMapping("/getAll")
    public Response<List<ClassDTO>> findAll(@RequestParam(value = "page",defaultValue = "0") Integer page,
                                            @RequestParam(value = "size", defaultValue = "10") Integer size) throws Exception {
        Pageable pageable = PageRequest.of(page, size);
        return Response.ok(classService.findAll(pageable));
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

    @GetMapping("/getClassByUserId/{userId}")
    public Response<List<ClassDTO>> getClassByOwnerId(@PathVariable(value = "userId", required = false) Long userId,
                                                      @RequestParam(value = "page", defaultValue = "0") Integer page,
                                                      @RequestParam(value = "size", defaultValue = "10") Integer size) throws Exception {
        Pageable pageable = PageRequest.of(page, size);
        return Response.ok(classService.findByOwnerId(userId,pageable));
    }

    @PostMapping("/join-class")
    public void joinClass(@RequestBody JoinClassDTO joinClassDTO) throws Exception {
        classService.joinClass(joinClassDTO);
    }

    @PostMapping("/class-approval")
    public void classApproval(@RequestBody ApproveClassUserDTO approveClassUserDTO) throws Exception {
        classService.classApproval(approveClassUserDTO);
    }
}
