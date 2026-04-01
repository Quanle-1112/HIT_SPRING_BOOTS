package org.example.btvn3.service;


import org.example.btvn3.dto.CreateStudentRequest;
import org.example.btvn3.dto.UpdateStudentRequest;
import org.example.btvn3.model.Student;

import java.util.List;

public interface StudentService {
    List<Student> getAllStudents();
    Student getStudentById(Long id);
    Student createStudent(CreateStudentRequest request);
    Student updateStudent(Long id, UpdateStudentRequest request);
    void deleteStudent(Long id);
    List<Student> getStudentsByMajor(String major);
    List<Student> getHonorStudents();
}