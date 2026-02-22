package com.courses.service;

import com.courses.dto.*;
import com.courses.models.Test;

import java.util.List;

public interface TestService {

    Test getTestById(int testId);
    List<Test> getTestsByCourseId(int courseId);
    List<UserTestDTO> getUserTests(String token);
    List<UserResultsDTO> getAllUserTestsForAdmin();
    List<Test> getAllTests(String token);

    String deleteTest(Integer testId);

    TestPassDTO submitTest(TestSubmissionDTO testSubmissionDTO, String token);
    Test createTest(CreateTestDTO createTestDTO);

}
