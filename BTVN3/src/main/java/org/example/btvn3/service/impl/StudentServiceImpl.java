package org.example.btvn3.service.impl;


import org.example.btvn3.dto.CreateStudentRequest;
import org.example.btvn3.dto.UpdateStudentRequest;
import org.example.btvn3.exception.DuplicateResourceException;
import org.example.btvn3.exception.ResourceNotFoundException;
import org.example.btvn3.model.Student;
import org.example.btvn3.service.StudentService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {

    private final List<Student> students = new ArrayList<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    @Override
    public List<Student> getAllStudents() {
        return students;
    }

    @Override
    public Student getStudentById(Long id) {
        return students.stream()
                .filter(s -> s.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy sinh viên với ID: " + id));
    }

    @Override
    public Student createStudent(CreateStudentRequest request) {
        boolean codeExists = students.stream().anyMatch(s -> s.getStudentCode().equals(request.getStudentCode()));
        if (codeExists) throw new DuplicateResourceException("Mã sinh viên đã tồn tại");

        boolean emailExists = students.stream().anyMatch(s -> s.getEmail().equals(request.getEmail()));
        if (emailExists) throw new DuplicateResourceException("Email đã tồn tại");

        Student newStudent = new Student(
                idGenerator.getAndIncrement(),
                request.getStudentCode(),
                request.getName(),
                request.getEmail(),
                request.getPhone(),
                request.getDateOfBirth(),
                request.getGpa(),
                request.getMajor(),
                request.getYear()
        );
        students.add(newStudent);
        return newStudent;
    }

    @Override
    public Student updateStudent(Long id, UpdateStudentRequest request) {
        Student student = getStudentById(id);

        boolean emailExists = students.stream()
                .anyMatch(s -> s.getEmail().equals(request.getEmail()) && !s.getId().equals(id));
        if (emailExists) throw new DuplicateResourceException("Email đã được sử dụng bởi sinh viên khác");

        student.setName(request.getName());
        student.setEmail(request.getEmail());
        student.setPhone(request.getPhone());
        student.setDateOfBirth(request.getDateOfBirth());
        student.setGpa(request.getGpa());
        student.setMajor(request.getMajor());
        student.setYear(request.getYear());

        return student;
    }

    @Override
    public void deleteStudent(Long id) {
        Student student = getStudentById(id);
        students.remove(student);
    }

    @Override
    public List<Student> getStudentsByMajor(String major) {
        return students.stream()
                .filter(s -> s.getMajor().equalsIgnoreCase(major))
                .collect(Collectors.toList());
    }

    @Override
    public List<Student> getHonorStudents() {
        return students.stream()
                .filter(s -> s.getGpa() >= 3.6)
                .collect(Collectors.toList());
    }
}