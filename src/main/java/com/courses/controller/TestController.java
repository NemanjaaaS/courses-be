package com.courses.controller;

import com.courses.dto.*;
import com.courses.models.Test;
import com.courses.service.QuestionService;
import com.courses.service.TestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${courses.api.url}/test")
@RequiredArgsConstructor
@CrossOrigin
public class TestController {

    private final TestService testService;
    private final QuestionService questionService;

    @GetMapping("/by-course/{courseId}")
    public ResponseEntity<List<Test>> getTestsByCourseId(@PathVariable int courseId) {
        return ResponseEntity.ok(testService.getTestsByCourseId(courseId));
    }

    @GetMapping("/all")
    public ResponseEntity<List<Test>> allTests(@RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(testService.getAllTests(token));
    }

    @GetMapping("admin-tests-results")
    public ResponseEntity<List<UserResultsDTO>> testResults(){
        return ResponseEntity.ok(testService.getAllUserTestsForAdmin());
    }



    @GetMapping("/my-tests")
    public ResponseEntity<List<UserTestDTO>> currentUserTests(@RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(testService.getUserTests(token));
    }

    @GetMapping("/questions/{testId}")
    public ResponseEntity<List<SingleQuestionDTO>> getQuestionsForTest(@PathVariable int testId) {
        return ResponseEntity.ok(questionService.getQuestionsForTest(testId));
    }

    @PostMapping("/submit")
    public ResponseEntity<TestPassDTO> submitTest(@RequestBody TestSubmissionDTO testSubmissionDTO,
                                                  @RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(testService.submitTest(testSubmissionDTO, token));
    }

}
