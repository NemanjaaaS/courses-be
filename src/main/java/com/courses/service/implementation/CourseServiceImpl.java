package com.courses.service.implementation;

import com.courses.common.service.JwtService;
import com.courses.dto.CertificateDTO;
import com.courses.dto.CourseTableDTO;
import com.courses.dto.CreateCourseDTO;
import com.courses.dto.ResponseDTO;
import com.courses.exception.NotFoundException;
import com.courses.models.Course;
import com.courses.models.Enrollment;
import com.courses.models.User;
import com.courses.repositories.CourseRepository;
import com.courses.service.CourseRequestService;
import com.courses.service.CourseService;
import com.courses.service.EnrollmentService;
import com.courses.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final CourseRequestService courseRequestService;
    private final JwtService jwtService;
    private final UserService userService;
    private final EnrollmentService enrollmentService;

    @Override
    public Course getCourseById(int id) {
        return courseRepository.findById(id).orElseThrow(() -> new NotFoundException("Course not found!"));
    }

    @Override
    public List<CourseTableDTO> getCourses(String token) {

        String email = jwtService.getEmailFromUnsplitToken(token);
        List<Course> courses = courseRepository.findAll();
        return courses.stream().map(course -> new CourseTableDTO(course, courseRequestService.isUserRequestedCourse(course, email))).toList();
    }

    @Override
    public ResponseDTO requestCourse(int courseId, String token) {
        return courseRequestService.requestCourse(getCourseById(courseId), jwtService.getEmailFromUnsplitToken(token));
    }

    @Override
    public List<Course> getAllUserCourses(String token) {
        return enrollmentService.getAllUserEnrollments(userService.getUserByEmail(jwtService.getEmailFromUnsplitToken(token))).stream().map(Enrollment::getCourse).toList();
    }

    @Override
    public Integer updateCoursePercentage(Course course, User user, int percentage) {
        return enrollmentService.updateProgressPercentage(course, user, percentage);
    }

    @Override
    public List<CertificateDTO> getUserCertificates(String token) {
        User user = userService.getUserByToken(token);
        List<Enrollment> finishedEnrollments = enrollmentService.getFinishedEnrollment(user);
        List<CertificateDTO> certificateDTOS = new ArrayList<>();
        for (Enrollment enrollment : finishedEnrollments) {
            certificateDTOS.add(new CertificateDTO(enrollment.getCourse().getTitle(), user.getFullName(), enrollment.getCompletedAt()));
        }
        return certificateDTOS;
    }

    @Override
    public Course createCourse(CreateCourseDTO createCourseDTO) {
        return courseRepository.save(new Course(createCourseDTO));
    }

}
