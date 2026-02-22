package com.courses.dto;

import com.courses.models.Course;
import com.courses.models.Test;
import lombok.Data;

@Data
public class UserTestDTO {

    private int id;

    private String title;

    private Integer numberOfQuestions;

    private Integer durationMinutes;

    private Integer passingScorePercentage;

    public boolean attended;

    public boolean passed;

    public Course course;

    public UserTestDTO(Test test, Boolean isAttended, Boolean isPassed) {
        this.id = test.getId();
        this.title = test.getTitle();
        this.numberOfQuestions = test.getNumberOfQuestions();
        this.durationMinutes = test.getDurationMinutes();
        this.passingScorePercentage = test.getPassingScorePercentage();
        this.attended = isAttended;
        this.passed = isPassed;
        this.course = test.getCourse();
    }

}
