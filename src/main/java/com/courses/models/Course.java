package com.courses.models;

import com.courses.dto.CreateCourseDTO;
import com.courses.models.enums.CourseCategory;
import com.courses.models.enums.CourseLevel;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;

@Entity
@Table(name = "courses")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String shortDescription;

    @Enumerated(EnumType.STRING)
    private CourseCategory category;

    @Enumerated(EnumType.STRING)
    private CourseLevel level;

    private Integer durationHours;

    private Double price;

    private Double rating;

    private Integer enrolledCount;

    private Boolean active;

    private Timestamp createdAt;

    private Timestamp updatedAt;

    public Course(CreateCourseDTO createCourseDTO) {
        this.title = createCourseDTO.getTitle();
        this.shortDescription = createCourseDTO.getShortDescription();
        this.category = createCourseDTO.getCategory();
        this.level = createCourseDTO.getLevel();
        this.durationHours = createCourseDTO.getDurationHours();
        this.price = createCourseDTO.getPrice();
        this.rating = createCourseDTO.getRating();
        this.enrolledCount = 0;
    }

    @PrePersist
    protected void onCreate() {
        this.createdAt = new Timestamp(System.currentTimeMillis());
        this.updatedAt = new Timestamp(System.currentTimeMillis());
        if (this.enrolledCount == null) {
            this.enrolledCount = 0;
        }
        if (this.active == null) {
            this.active = true;
        }
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = new Timestamp(System.currentTimeMillis());
    }

}
