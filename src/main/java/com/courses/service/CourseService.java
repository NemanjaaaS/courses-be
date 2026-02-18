package com.courses.service;

import com.courses.dto.CertificateDTO;
import com.courses.dto.CourseTableDTO;
import com.courses.dto.CreateCourseDTO;
import com.courses.dto.ResponseDTO;
import com.courses.models.Course;
import com.courses.models.User;
import com.courses.models.enums.CourseCategory;
import com.courses.models.enums.CourseLevel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CourseService {

    Course getCourseById(int id);
    List<CourseTableDTO> getCourses(String token);
    ResponseDTO requestCourse(int courseId, String token);

    List<Course> getAllUserCourses(String token);

    Integer updateCoursePercentage(Course course, User user, int percentage);

    List<CertificateDTO> getUserCertificates(String token);

    Course createCourse(CreateCourseDTO createCourseDTO);

    String deleteCourse(Integer courseId);

}
