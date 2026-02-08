package com.courses.service;

import com.courses.dto.*;
import com.courses.models.Question;
import com.courses.models.Test;

import java.util.List;

public interface QuestionService {

    List<SingleQuestionDTO> getQuestionsForTest(int testId);
    TestPassDTO submitQuestions(Test test, List<QuestionAnswerDTO> answers);
    Question createQuestion(CreateTestDTO.CreateQuestionDTO createQuestionDTO, Test test);

}
