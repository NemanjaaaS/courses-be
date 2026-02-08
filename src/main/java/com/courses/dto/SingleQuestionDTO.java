package com.courses.dto;

import com.courses.models.Question;
import lombok.Data;

import java.util.List;

@Data
public class SingleQuestionDTO {

    private int id;

    private String text;

    private List<String> options;

    public SingleQuestionDTO(Question question) {
        this.id = question.getId();
        this.text = question.getText();
        this.options = question.getOptions();
    }

}
