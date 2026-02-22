package com.courses.dto;

import lombok.Data;

@Data
public class UserTableDTO {

    private int id;

    private String firstName;

    private String lastName;

    private String email;

    private int enrolledCourses;

    private int completedCourses;

    private int passedTests;

    private int failedTests;
}
