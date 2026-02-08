package com.courses.models;

import com.courses.dto.CreateTestDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Entity
@Table(name = "tests")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Test {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String title;

    private Integer numberOfQuestions;

    private Integer durationMinutes;

    private Integer passingScorePercentage;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id")
    private Course course;

    private Timestamp createdAt;

    private Timestamp updatedAt;

    public Test(CreateTestDTO createTestDTO, Course course) {
        this.title = createTestDTO.getTitle();
        this.numberOfQuestions = createTestDTO.getCreateQuestionDTOS().size();
        this.durationMinutes = createTestDTO.getDurationMinutes();
        this.passingScorePercentage = createTestDTO.getPassingScorePercentage();
        this.course = course;
    }

    @PrePersist
    protected void onCreate() {
        this.createdAt = new Timestamp(System.currentTimeMillis());
        this.updatedAt = new Timestamp(System.currentTimeMillis());
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = new Timestamp(System.currentTimeMillis());
    }

}
