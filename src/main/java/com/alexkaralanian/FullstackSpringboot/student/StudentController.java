package com.alexkaralanian.FullstackSpringboot.student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController // Declares Class a REST controller
@RequestMapping("students") // Declares request path "/student"

// Endpoints should direct traffic to services
// Services perform all business logic for the application
// DAO (Data Access) Layer which is resposible for database operations

public class StudentController {
    // Declares a "GET" method.

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
         this.studentService = studentService;
    }

    @GetMapping
    public List<Student> getAllStudents(){
        return studentService.getAllStudents();
    }

    @PostMapping
    public void addNewStudent(@RequestBody Student student){
        studentService.addNewStudent(student);
    }
}
