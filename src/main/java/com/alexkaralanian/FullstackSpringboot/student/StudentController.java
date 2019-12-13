package com.alexkaralanian.FullstackSpringboot.student;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController // Declares Class a REST controller
@RequestMapping("students") // Declares request path "/student"

public class StudentController {
    // Declares a "GET" method.
    @GetMapping
    public List<Student> getAllStudents(){
        return List.of(
            new Student(UUID.randomUUID(), "James", "Bond", "jamesbond@gmail.com", Student.Gender.MALE),
            new Student(UUID.randomUUID(), "Sarah", "Drasner", "sarah.drasner@gmail.com", Student.Gender.FEMALE)
        );
    }
}
