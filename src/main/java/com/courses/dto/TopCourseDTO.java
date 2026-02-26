package com.courses.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TopCourseDTO {
    private String courseName;
    private int numberOfEnrollments;
}
