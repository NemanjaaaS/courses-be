package com.courses.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TestPassDTO {

    private int percentage;
    private boolean isPassed;

    public TestPassDTO(int correctAnswers, int totalAnswers, int passPercentage) {
        this.percentage = (int) (((double) correctAnswers / totalAnswers) * 100);
        this.isPassed = this.percentage >= passPercentage;
    }

}
