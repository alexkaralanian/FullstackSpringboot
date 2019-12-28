package com.alexkaralanian.FullstackSpringboot.student;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public class Student {
    // Class fields
    // 'private' access modifier encapsulates the fields and methods from public access.
    // 'final' variable cannot be changed. can only be initialized once.
    private final UUID studentId;
    private final String firstName;
    private final String lastName;
    private final String email;
    private final Gender gender;

    // The constructor initializes  / sets state
    // Called once during initialization to set Class fields.
    public Student(
            @JsonProperty("studentId") UUID studentId,
            @JsonProperty("firstName") String firstName,
            @JsonProperty("lastName") String lastName,
            @JsonProperty("email") String email,
            @JsonProperty("gender") Gender gender) {
        this.studentId = studentId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.gender = gender;
    }

    // Getter Methods (no need for setters as using constructor with 'final' Class fields.
    public UUID getStudentId() { return studentId; }
    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public String getEmail() {
        return email;
    }
    public Gender getGender() {
        return gender;
    }
    enum Gender { MALE, FEMALE }

    @Override
    public String toString() {
        return "Student{" +
                "studentId=" + studentId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", gender=" + gender +
                '}';
    }
}
