package com.courses.controller;

import com.courses.dto.*;
import com.courses.models.Course;
import com.courses.models.Test;
import com.courses.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${courses.api.url}/admin")
@RequiredArgsConstructor
@CrossOrigin
public class AdminController {

    private final CourseRequestService courseRequestService;
    private final CourseService courseService;
    private final TestService testService;
    private final UserService userService;
    private final DashboardService dashboardService;

    @PostMapping("/change-course-status")
    public ResponseEntity<ResponseDTO> changeCourseStatus(@RequestBody ChangeRequestStatusDTO changeRequestStatusDTO) {
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

    @DeleteMapping("/delete-course/{courseId}")
    public ResponseEntity<String> deleteCourse(@PathVariable Integer courseId){
        return ResponseEntity.ok(courseService.deleteCourse(courseId));
    }
    @DeleteMapping("/delete-test/{testId}")
    public ResponseEntity<String> deleteTest(@PathVariable Integer testId){
        return ResponseEntity.ok(testService.deleteTest(testId));
    }

    @GetMapping("/all-users")
    public ResponseEntity<List<UserTableDTO>> allUsers(){
        return ResponseEntity.ok(userService.getAllUsersForTable());
    }

    @GetMapping("/dashboard")
    public DashboardDTO getDashboard() {
        return dashboardService.getAdminDashboard();
    }


}
