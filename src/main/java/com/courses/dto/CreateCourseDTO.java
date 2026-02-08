package com.courses.dto;

import com.courses.models.enums.CourseCategory;
import com.courses.models.enums.CourseLevel;
import lombok.Data;

@Data
public class CreateCourseDTO {

    private String title;
    private String shortDescription;
    private CourseCategory category;
    private CourseLevel level;
    private int durationHours;
    private double price;
    private double rating;

}
