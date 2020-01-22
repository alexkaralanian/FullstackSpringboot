package com.alexkaralanian.FullstackSpringboot.student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
// DAO layer is responsible for connecting to DB - saving, retreiving, etc…
public class StudentDAO { // Data Access Service

    // Class fields
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    // Class Constructor
    public StudentDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    int insertStudent(UUID studentId, Student student) {

        String sql = "" +
                "INSERT INTO student (student_id, first_name, last_name, email, gender) " +
                "VALUES (?, ?, ?, ?, ?::gender)";

        return jdbcTemplate.update(
                sql,
                studentId,
                student.getFirstName(),
                student.getLastName(),
                student.getEmail(),
                student.getGender().name().toUpperCase()
        );
    }

    // Class Methods
    List<Student> selectAllStudents(){
        String sql = "" +
                "SELECT " +
                "student_id, " +
                "first_name, " +
                "last_name, " +
                "email, " +
                "gender " +
                "FROM student"
        ;

        // Helper function to access the result set from DB.
        return jdbcTemplate.query(sql, mapStudentFromDb()); // refactored...
    }

    // Refactor > extract > Method || opt+cmd+m
    // mapStudentFromDb()
    private RowMapper<Student> mapStudentFromDb() {
        return (resultSet, i) -> {
            String studentIdStr = resultSet.getString("student_id");
            UUID studentId = UUID.fromString(studentIdStr);
            String firstName = resultSet.getString("first_name");
            String lastName = resultSet.getString("last_name");
            String email = resultSet.getString("email");
            String genderStr = resultSet.getString("gender").toUpperCase();
            Student.Gender gender = Student.Gender.valueOf(genderStr);

            // Return POJO / Student instance.
            return new Student (
                    studentId,
                    firstName,
                    lastName,
                    email,
                    gender
            );
        };
    }
}
