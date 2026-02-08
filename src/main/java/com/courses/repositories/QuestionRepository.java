package com.courses.repositories;

import com.courses.models.Question;
import com.courses.models.Test;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Integer> {

    List<Question> getQuestionsByTest(Test test);

}
