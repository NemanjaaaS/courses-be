package com.courses.dto;

import lombok.Data;

import java.util.List;

@Data
public class TestSubmissionDTO {

    private List<QuestionAnswerDTO> answers;
    private int testId;

}
