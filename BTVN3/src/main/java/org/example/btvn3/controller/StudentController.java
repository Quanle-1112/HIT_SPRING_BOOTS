package org.example.btvn3.controller;


import jakarta.validation.Valid;
import org.example.btvn3.dto.CreateStudentRequest;
import org.example.btvn3.dto.UpdateStudentRequest;
import org.example.btvn3.model.Student;
import org.example.btvn3.response.ApiResponse;
import org.example.btvn3.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Student>>> getAll() {
        return ResponseEntity.ok(ApiResponse.success(studentService.getAllStudents()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Student>> getById(@PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.success(studentService.getStudentById(id)));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Student>> create(@Valid @RequestBody CreateStudentRequest request) {
        Student createdStudent = studentService.createStudent(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.created(createdStudent));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Student>> update(@PathVariable Long id, @Valid @RequestBody UpdateStudentRequest request) {
        Student updatedStudent = studentService.updateStudent(id, request);
        return ResponseEntity.ok(ApiResponse.success(updatedStudent));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return ResponseEntity.ok(ApiResponse.success(null));
    }

    @GetMapping("/major/{major}")
    public ResponseEntity<ApiResponse<List<Student>>> getByMajor(@PathVariable String major) {
        return ResponseEntity.ok(ApiResponse.success(studentService.getStudentsByMajor(major)));
    }

    @GetMapping("/honors")
    public ResponseEntity<ApiResponse<List<Student>>> getHonors() {
        return ResponseEntity.ok(ApiResponse.success(studentService.getHonorStudents()));
    }
}