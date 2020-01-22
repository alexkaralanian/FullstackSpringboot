package com.alexkaralanian.FullstackSpringboot.student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class StudentDAO { // Data Access Service

    private final JdbcTemplate jdbcTemplate;

    @Autowired
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

    List<StudentCourse> selectAllStudentCourses(UUID studentId){
        String sql = "" +
                "SELECT " +
                " student.student_id, " +
                " course.course_id, " +
                " course.name, " +
                " course.description, " +
                " course.department, " +
                " course.teacher_name, " +
                " student_course.start_date, " +
                " student_course.end_date, " +
                " student_course.grade " +
                "FROM student " +
                "JOIN student_course USING (student_id) " +
                "JOIN course USING (course_id) " +
                "WHERE student.student_id = ?"; // parameterized

        return jdbcTemplate.query(
                sql,
                // ? passes in parameters
                new Object[]{studentId},
                mapStudentCourseFromDb()
        );
    }

    private RowMapper<Student> mapStudentFromDb() {
        return (resultSet, i) -> {
            String studentIdStr = resultSet.getString("student_id");
            UUID studentId = UUID.fromString(studentIdStr);
            String firstName = resultSet.getString("first_name");
            String lastName = resultSet.getString("last_name");
            String email = resultSet.getString("email");
            String genderStr = resultSet.getString("gender").toUpperCase();
            Student.Gender gender = Student.Gender.valueOf(genderStr);

            return new Student (
                    studentId,
                    firstName,
                    lastName,
                    email,
                    gender
            );
        };
    }

    private RowMapper<StudentCourse> mapStudentCourseFromDb() {
        return (resultSet, i) ->
           new StudentCourse(
                   UUID.fromString(resultSet.getString("student_id")),
                   UUID.fromString(resultSet.getString("course_id")),
                   resultSet.getString("name"),
                   resultSet.getString("description"),
                   resultSet.getString("department"),
                   resultSet.getString("teacher_name"),
                   resultSet.getDate("start_date").toLocalDate(),
                   resultSet.getDate("end_date").toLocalDate(),
                   Optional.ofNullable(resultSet.getString("grade"))
                           .map(Integer::parseInt)
                           .orElse(null)
            );
    }
}
