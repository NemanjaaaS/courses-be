package com.courses.service.implementation;

import com.courses.dto.*;
import com.courses.exception.NotFoundException;
import com.courses.models.Course;
import com.courses.models.EnrolledTest;
import com.courses.models.Test;
import com.courses.models.User;
import com.courses.repositories.EnrolledTestRepository;
import com.courses.repositories.QuestionRepository;
import com.courses.repositories.TestRepository;
import com.courses.service.CourseService;
import com.courses.service.QuestionService;
import com.courses.service.TestService;
import com.courses.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class TestServiceImpl implements TestService {

    private final TestRepository testRepository;
    private final CourseService courseService;
    private final QuestionRepository questionRepository;
    @Lazy
    private final QuestionService questionService;
    private final EnrolledTestRepository enrolledTestRepository;
    private final UserService userService;


    @Override
    public Test getTestById(int testId) {
        return testRepository.findById(testId).orElseThrow(() -> new NotFoundException("Test not found!"));
    }

    @Override
    public List<Test> getTestsByCourseId(int courseId) {
        Course course = courseService.getCourseById(courseId);
        return testRepository.getTestByCourse(course);
    }

    public List<UserTestDTO> getUserTests(String token) {
        List<Test> tests = courseService.getAllUserCourses(token).stream().map(Course::getId).flatMap(id -> getTestsByCourseId(id.intValue()).stream()).toList();

        List<UserTestDTO> userTestDTOS = new ArrayList<>();

        User user = userService.getUserByToken(token);

        tests.forEach(test -> {
            boolean isAttended = enrolledTestRepository.existsByUserAndTest(user, test);
            if (isAttended) {
                userTestDTOS.add(new UserTestDTO(test, true, enrolledTestRepository.isUserPassed(user, test)));
            } else {
                userTestDTOS.add(new UserTestDTO(test, false, false));
            }
        });

        return userTestDTOS;
    }

    @Override
    public List<UserResultsDTO> getAllUserTestsForAdmin() {

        List<EnrolledTest> enrollments = enrolledTestRepository.findAll();

        return enrollments.stream()
                .map(enrollment -> {
                    Test test = enrollment.getTest();
                    User user = enrollment.getUser();
                    int userPercentage = enrollment.getPercentage();

                    boolean passed = enrollment.isPassed();

                    return new UserResultsDTO(
                            test,
                            true,
                            passed,
                            user,
                            userPercentage
                    );
                })
                .toList();
    }

    @Override
    public List<Test> getAllTests(String token) {
        return testRepository.findAll();
    }

    @Override
    @Transactional
    public String deleteTest(Integer testId) {
        questionRepository.deleteAllByTest(getTestById(testId));
        testRepository.deleteById(testId);
        return "Test deleted successfully!";
    }

    @Override
    public TestPassDTO submitTest(TestSubmissionDTO testSubmissionDTO, String token) {
        Test test = getTestById(testSubmissionDTO.getTestId());
        User user = userService.getUserByToken(token);
        TestPassDTO testPassDTO = questionService.submitQuestions(test, testSubmissionDTO.getAnswers());
        enrolledTestRepository.save(new EnrolledTest(test, user, testPassDTO));
        updateCoursePercentage(test.getCourse(), user);
        return testPassDTO;
    }

    public Test createTest(CreateTestDTO createTestDTO) {
        Test test = testRepository.save(new Test(createTestDTO, courseService.getCourseById(createTestDTO.getCourseId())));
        createTestDTO.getCreateQuestionDTOS().forEach(question -> questionService.createQuestion(question, test));
        return test;
    }

    public Integer updateCoursePercentage(Course course, User user) {
        int usersPassedTests = enrolledTestRepository.getPassedTestsCountForUser(course.getId().intValue(), user.getId());
        int totalTests = testRepository.countByCourse(course);
        int passedPercentage = (int) (((double) usersPassedTests / totalTests) * 100);
        return courseService.updateCoursePercentage(course, user, passedPercentage);
    }

}
