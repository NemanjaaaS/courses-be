package com.courses.controller;

import com.courses.dto.CertificateDTO;
import com.courses.dto.CourseTableDTO;
import com.courses.dto.ResponseDTO;
import com.courses.models.Course;
import com.courses.models.CourseRequest;
import com.courses.models.enums.CourseCategory;
import com.courses.models.enums.CourseLevel;
import com.courses.service.CourseRequestService;
import com.courses.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${courses.api.url}/course")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class CourseController {

    private final CourseService courseService;
    private final CourseRequestService courseRequestService;

    @GetMapping("/all")
    public List<CourseTableDTO> getCourses(@RequestHeader("Authorization") String token) {
        return courseService.getCourses(token);
    }

    @GetMapping("/requested-courses")
    public List<CourseRequest> getRequestedCourses(@RequestHeader("Authorization") String token){
        return  courseRequestService.getRequestedCourses(token);
    }

    @PostMapping("/request-course/{courseId}")
    public ResponseEntity<ResponseDTO> requestCourse(@PathVariable int courseId,
                                                     @RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(courseService.requestCourse(courseId, token));
    }

    @GetMapping("/certificates")
    public ResponseEntity<List<CertificateDTO>> getUserCertificates(@RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(courseService.getUserCertificates(token));
    }

}
