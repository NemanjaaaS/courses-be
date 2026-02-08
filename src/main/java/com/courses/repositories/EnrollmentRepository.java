package com.courses.repositories;

import com.courses.models.Course;
import com.courses.models.Enrollment;
import com.courses.models.User;
import com.courses.models.keys.EnrollmentKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EnrollmentRepository extends JpaRepository<Enrollment, EnrollmentKey> {

    List<Enrollment> getEnrollmentsByUser(User user);

    Optional<Enrollment> getEnrollmentByCourseAndUser(Course course, User user);

    List<Enrollment> getAllByUserAndIsCompletedTrue(User user);

}
