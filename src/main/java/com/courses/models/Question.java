package com.courses.models;

import com.courses.converter.StringListConverter;
import com.courses.dto.CreateTestDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "questions")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String text;

    @Convert(converter = StringListConverter.class)
    private List<String> options;

    private String correctAnswer;

    @ManyToOne
    @JoinColumn(name = "test_id")
    private Test test;

    private Timestamp createdAt;

    private Timestamp updatedAt;

    public Question(CreateTestDTO.CreateQuestionDTO createQuestionDTO, Test test) {
        this.text = createQuestionDTO.text();
        this.options = createQuestionDTO.options();
        this.correctAnswer = createQuestionDTO.correctAnswer();
        this.test = test;
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
