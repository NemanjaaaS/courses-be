package com.courses.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserCourseProgressDTO {

    private String courseName;
    private long completedTests;
    private long totalTests;
}