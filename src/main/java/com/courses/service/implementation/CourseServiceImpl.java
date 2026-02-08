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
import com.courses.models.enums.CourseCategory;
import com.courses.models.enums.CourseLevel;
import com.courses.repositories.CourseRepository;
import com.courses.service.CourseRequestService;
import com.courses.service.CourseService;
import com.courses.service.EnrollmentService;
import com.courses.service.UserService;
import com.courses.specification.CourseSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
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
    public Page<CourseTableDTO> getCourses(String search,
                                   CourseCategory category,
                                   CourseLevel level,
                                   Pageable pageable,
                                   String token) {
        Specification<Course> spec = Specification
                .where(CourseSpecification.isActive())
                .and(CourseSpecification.titleContains(search))
                .and(CourseSpecification.hasCategory(category))
                .and(CourseSpecification.hasLevel(level));

        String email = jwtService.getEmailFromUnsplitToken(token);
        return courseRepository.findAll(spec, pageable).map(course -> new CourseTableDTO(course, courseRequestService.isUserRequestedCourse(course, email)));
    }

    @Override
    public ResponseDTO requestCourse(int courseId, String token) {
        return courseRequestService.requestCourse(getCourseById(courseId), jwtService.getEmailFromUnsplitToken(token));
    }

    @Override
    public List<Course> getAllUserCourses(String token) {
        return enrollmentService.getAllUserEnrollments(
                userService.getUserByEmail(jwtService.getEmailFromUnsplitToken(token))
        )
                .stream()
                .map(Enrollment::getCourse)
                .toList();
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
