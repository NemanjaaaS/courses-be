package com.courses.dto;

import lombok.Data;

import java.util.List;

@Data
public class CreateTestDTO {

    private int courseId;
    private String title;
    private int durationMinutes;
    private int passingScorePercentage;
    private List<CreateQuestionDTO> createQuestionDTOS;

    public record CreateQuestionDTO(String text, List<String> options, String correctAnswer) {}

}
