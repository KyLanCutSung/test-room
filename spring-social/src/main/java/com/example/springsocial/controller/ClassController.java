package com.example.springsocial.controller;

import com.example.springsocial.payload.auth_payload.ApiResponse;
import com.example.springsocial.payload.class_document_payload.ClassDocumentDTO;
import com.example.springsocial.payload.class_payload.ActiveDocumentInClassDTO;
import com.example.springsocial.payload.class_payload.ClassDTO;
import com.example.springsocial.payload.class_payload.JoinClassDTO;
import com.example.springsocial.payload.class_user_payload.ApproveClassUserDTO;
import com.example.springsocial.service.ClassService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Page<ClassDTO>> findAll(@RequestParam(value = "page",defaultValue = "0") Integer page,
                                                  @RequestParam(value = "size", defaultValue = "10") Integer size) throws Exception {
        Pageable pageable = PageRequest.of(page, size);
        return new ResponseEntity<>(classService.findAll(pageable), HttpStatus.OK);
    }

    @PostMapping("/create")
    @PreAuthorize("hasAnyRole('TEACHER','ADMIN')")
    public ResponseEntity<ClassDTO> create(@RequestBody ClassDTO classDTO) throws Exception {
        return new ResponseEntity<>(classService.create(classDTO),HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    @PreAuthorize("hasAnyRole('TEACHER','ADMIN')")
    public ResponseEntity<ApiResponse> deleteClass(@RequestParam(value = "classId", required = false) Long classId,
                                        @RequestParam(value = "userId", required = false) Long userId) throws Exception {
    return new ResponseEntity<>(classService.delete(classId,userId),HttpStatus.OK) ;
    }

    @GetMapping("/getClassByUserId/{userId}")
    @PreAuthorize("hasAnyRole('TEACHER','STUDENT')")
    public ResponseEntity<List<ClassDTO>> getClassByOwnerId(@PathVariable(value = "userId", required = false) Long userId,
                                                      @RequestParam(value = "page", defaultValue = "0") Integer page,
                                                      @RequestParam(value = "size", defaultValue = "10") Integer size){
        Pageable pageable = PageRequest.of(page, size);
        return new ResponseEntity<>(classService.findByOwnerId(userId,pageable).getContent(),HttpStatus.OK);
    }

    @PostMapping("/join-class")
    public ResponseEntity<ApiResponse> joinClass(@RequestBody JoinClassDTO joinClassDTO) throws Exception {
        return new ResponseEntity<>(classService.joinClass(joinClassDTO),HttpStatus.OK);
    }

    @PostMapping("/class-approval")
    @PreAuthorize("hasAnyRole('TEACHER')")
    public ResponseEntity<ApiResponse> classApproval(@RequestBody ApproveClassUserDTO approveClassUserDTO) throws Exception {
        return new ResponseEntity<>(classService.classApproval(approveClassUserDTO), HttpStatus.OK);
    }

    @PostMapping("/active-document")
    @PreAuthorize("hasRole('TEACHER')")
    public ResponseEntity<ApiResponse> activeDocumentForClass(@RequestBody List<ClassDocumentDTO> classDocumentDTOS) {
        return new ResponseEntity<>(classService.activeDocument(classDocumentDTOS),HttpStatus.OK);
    }

    @PostMapping("/deactivate-document")
    @PreAuthorize("hasRole('TEACHER')")
    public ResponseEntity<ApiResponse> deactivateDocument(@RequestBody List<ClassDocumentDTO> classDocumentDTOS) {
        return new ResponseEntity<>(classService.deactivateDocument(classDocumentDTOS),HttpStatus.OK);
    }

    @GetMapping("/documents/{classId}")
    public ResponseEntity<List<ActiveDocumentInClassDTO>> findActiveDocumentInClass(@PathVariable("classId") Long classId) {
        return new ResponseEntity<>(classService.findActiveDocumentInClass(classId), HttpStatus.OK);
    }
}
