package com.courses.dto;

import com.courses.models.Course;
import com.courses.models.enums.CourseCategory;
import com.courses.models.enums.CourseLevel;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class CourseTableDTO {

    private Long id;

    private String title;

    private String shortDescription;

    private CourseCategory category;

    private CourseLevel level;

    private Integer durationHours;

    private Double price;

    private Double rating;

    private boolean isCourseRequested;

    public CourseTableDTO(Course course, boolean isCourseRequested) {
        this.id = course.getId();
        this.title = course.getTitle();
        this.shortDescription = course.getShortDescription();
        this.category = course.getCategory();
        this.level = course.getLevel();
        this.durationHours = course.getDurationHours();
        this.price = course.getPrice();
        this.rating = course.getRating();
        this.isCourseRequested = isCourseRequested;
    }

}
