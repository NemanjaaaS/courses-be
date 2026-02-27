package com.courses.service.implementation;

import com.courses.exception.NotFoundException;
import com.courses.models.Course;
import com.courses.models.Enrollment;
import com.courses.models.User;
import com.courses.repositories.EnrollmentRepository;
import com.courses.service.EnrollmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EnrollmentServiceImpl implements EnrollmentService {

    private final EnrollmentRepository enrollmentRepository;

    @Override
    public Enrollment createEnrollment(User user, Course course) {
        course.setEnrolledCount(course.getEnrolledCount() + 1);
        return enrollmentRepository.save(new Enrollment(user, course));
    }

    public List<Enrollment> getAllUserEnrollments(User user) {
        return enrollmentRepository.getEnrollmentsByUser(user);
    }

    @Override
    public Integer updateProgressPercentage(Course course, User user, int percentage) {
        Enrollment enrollment = enrollmentRepository.getEnrollmentByCourseAndUser(course, user)
                .orElseThrow(() -> new NotFoundException("Enrollment not found!"));
        enrollment.setProgressPercentage(percentage);

        if (percentage == 100) {
            enrollment.setIsCompleted(true);
            enrollment.setCompletedAt(new Timestamp(System.currentTimeMillis()));
        }

        enrollmentRepository.save(enrollment);
        return percentage;
    }

    @Override
    public List<Enrollment> getFinishedEnrollment(User user) {
        return enrollmentRepository.getAllByUserAndIsCompletedTrue(user);
    }

}
