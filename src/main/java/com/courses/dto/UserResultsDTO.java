package com.courses.dto;

import com.courses.models.Test;
import com.courses.models.User;
import lombok.Data;

import java.util.Objects;

@Data
public class UserResultsDTO {
    private int id;

    private String title;

    private Integer passingScorePercentage;

    private int userScore;

    public boolean attended;

    public boolean passed;

    public User user;

    public UserResultsDTO(Test test, Boolean isAttended, Boolean isPassed, User user, int percentage) {
        this.id = Objects.hash(test.getId(), user.getId()); // unique combination
        this.title = test.getTitle();
        this.passingScorePercentage = test.getPassingScorePercentage();
        this.attended = isAttended;
        this.passed = isPassed;
        this.user = user;
        this.userScore = percentage;
    }

}
