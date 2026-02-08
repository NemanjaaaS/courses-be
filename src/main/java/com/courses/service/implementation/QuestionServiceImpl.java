package com.courses.service.implementation;

import com.courses.dto.CreateTestDTO;
import com.courses.dto.QuestionAnswerDTO;
import com.courses.dto.SingleQuestionDTO;
import com.courses.dto.TestPassDTO;
import com.courses.models.Question;
import com.courses.models.Test;
import com.courses.repositories.QuestionRepository;
import com.courses.service.QuestionService;
import com.courses.service.TestService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService {

    private final QuestionRepository questionRepository;
    private final TestService testService;

    @Override
    public List<SingleQuestionDTO> getQuestionsForTest(int testId) {
        return questionRepository.getQuestionsByTest(testService.getTestById(testId))
                .stream()
                .map(SingleQuestionDTO::new)
                .toList();
    }

    @Override
    public TestPassDTO submitQuestions(Test test, List<QuestionAnswerDTO> answerDTOS) {
        int correctAnswers = 0;
        List<Question> questions = questionRepository.getQuestionsByTest(test);
        Map<Integer, String> correctAnswersMap = mapCorrectAnswers(questions);
        for (QuestionAnswerDTO questionAnswerDTO : answerDTOS) {
            if (correctAnswersMap.get(questionAnswerDTO.getQuestionId()).equals(questionAnswerDTO.getAnswer())) {
                correctAnswers++;
            }
        }
        return new TestPassDTO(correctAnswers, questions.size(), test.getPassingScorePercentage());
    }

    public Question createQuestion(CreateTestDTO.CreateQuestionDTO createQuestionDTO, Test test) {
        return questionRepository.save(new Question(createQuestionDTO, test));
    }

    private Map<Integer, String> mapCorrectAnswers(List<Question> questions) {
        Map<Integer, String> correctAnswers = new HashMap<>();
        for (Question question : questions) {
            correctAnswers.put(question.getId(), question.getCorrectAnswer());
        }
        return correctAnswers;
    }

}
