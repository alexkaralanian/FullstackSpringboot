package com.alexkaralanian.FullstackSpringboot.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class StudentService {
    // Service for business logic

    private final StudentDAO studentDAO;

    @Autowired
    public StudentService(StudentDAO studentDAO) {
        this.studentDAO = studentDAO;
    }

    // GET ALL STUDENTS
    List<Student> getAllStudents(){
        return studentDAO.selectAllStudents();
     }

    // ADD NEW STUDENT
    void addNewStudent(Student student) {
        addNewStudent(null, student);
    }

    void addNewStudent(UUID studentId, Student student) {
        UUID newStudentId = Optional.ofNullable(studentId)
                .orElse(UUID.randomUUID());
        // TODO: Check email is unique
        studentDAO.insertStudent(newStudentId, student);
    }

    // GET ALL STUDENT COURSES
    List<StudentCourse> getAllStudentCourses(UUID studentId) {
        return studentDAO.selectAllStudentCourses(studentId);
    }
}
