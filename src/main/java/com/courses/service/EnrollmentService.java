package com.courses.service;

import com.courses.models.Course;
import com.courses.models.Enrollment;
import com.courses.models.User;

import java.util.List;

public interface EnrollmentService {

    Enrollment createEnrollment(User user, Course course);
    List<Enrollment> getAllUserEnrollments(User user);

    Integer updateProgressPercentage(Course course, User user, int percentage);

    List<Enrollment> getFinishedEnrollment(User user);

}
