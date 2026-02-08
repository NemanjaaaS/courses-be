package com.courses.controller;

import com.courses.dto.ChangeRequestStatusDTO;
import com.courses.dto.CreateCourseDTO;
import com.courses.dto.CreateTestDTO;
import com.courses.dto.ResponseDTO;
import com.courses.models.Course;
import com.courses.models.Test;
import com.courses.service.CourseRequestService;
import com.courses.service.CourseService;
import com.courses.service.TestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("${courses.api.url}/admin")
@RequiredArgsConstructor
@CrossOrigin
public class AdminController {

    private final CourseRequestService courseRequestService;
    private final CourseService courseService;
    private final TestService testService;

    @PostMapping("/change-test-status")
    public ResponseEntity<ResponseDTO> changeTestStatus(@RequestBody ChangeRequestStatusDTO changeRequestStatusDTO) {
        return ResponseEntity.ok(courseRequestService.changeRequestStatus(changeRequestStatusDTO));
    }

    @PostMapping("/create-course")
    public ResponseEntity<Course> createCourse(@RequestBody CreateCourseDTO createCourseDTO) {
        return ResponseEntity.ok(courseService.createCourse(createCourseDTO));
    }

    @PostMapping("/create-test")
    public ResponseEntity<Test> createTest(@RequestBody CreateTestDTO createTestDTO) {
        return ResponseEntity.ok(testService.createTest(createTestDTO));
    }

}
