package com.example.springsocial.controller;

import com.example.springsocial.payload.Response;
import com.example.springsocial.payload.class_document_payload.ClassDocumentDTO;
import com.example.springsocial.payload.class_payload.ActiveDocumentInClassDTO;
import com.example.springsocial.payload.class_payload.ClassDTO;
import com.example.springsocial.payload.class_payload.JoinClassDTO;
import com.example.springsocial.payload.class_user_payload.ApproveClassUserDTO;
import com.example.springsocial.service.ClassService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/class")
@RequiredArgsConstructor
public class ClassController {
    private final ClassService classService;
    @GetMapping("/getAll")
    @PreAuthorize("hasAnyRole('TEACHER','ADMIN')")
    public Response<List<ClassDTO>> findAll(@RequestParam(value = "page",defaultValue = "0") Integer page,
                                            @RequestParam(value = "size", defaultValue = "10") Integer size) throws Exception {
        Pageable pageable = PageRequest.of(page, size);
        return Response.ok(classService.findAll(pageable));
    }

    @PostMapping("/create")
    @PreAuthorize("hasAnyRole('TEACHER','ADMIN')")
    public Response<ClassDTO> create(@RequestBody ClassDTO classDTO) throws Exception {
        return Response.created(classService.create(classDTO));
    }

    @DeleteMapping("/delete/{classId}")
    @PreAuthorize("hasAnyRole('TEACHER','ADMIN')")
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
    @PreAuthorize("hasAnyRole('TEACHER')")
    public void classApproval(@RequestBody ApproveClassUserDTO approveClassUserDTO) throws Exception {
        classService.classApproval(approveClassUserDTO);
    }

    @PostMapping("/active-document")
    @PreAuthorize("hasRole('TEACHER')")
    public void activeDocumentForClass(@RequestBody List<ClassDocumentDTO> classDocumentDTOS) {
        classService.activeDocument(classDocumentDTOS);
    }

    @PostMapping("/deactivate-document")
    @PreAuthorize("hasRole('TEACHER')")
    public void deactivateDocument(@RequestBody List<ClassDocumentDTO> classDocumentDTOS) {
        classService.deactivateDocument(classDocumentDTOS);
    }

    @GetMapping("/documents/{classId}")
    public Response<List<ActiveDocumentInClassDTO>> findActiveDocumentInClass(@PathVariable("classId") Long classId) {
        return Response.ok(classService.findActiveDocumentInClass(classId));
    }
}
