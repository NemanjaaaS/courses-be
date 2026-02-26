package com.courses.repositories;

import com.courses.models.Course;
import com.courses.models.Enrollment;
import com.courses.models.User;
import com.courses.models.keys.EnrollmentKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EnrollmentRepository extends JpaRepository<Enrollment, EnrollmentKey> {

    List<Enrollment> getEnrollmentsByUser(User user);

    Optional<Enrollment> getEnrollmentByCourseAndUser(Course course, User user);

    List<Enrollment> getAllByUserAndIsCompletedTrue(User user);

    @Query("SELECT COUNT(e) FROM Enrollment e")
    Long countAllEnrollments();

    @Query("SELECT COUNT(e) FROM Enrollment e WHERE e.isCompleted = true")
    Long countCompleted();

    @Query("SELECT AVG(e.progressPercentage) FROM Enrollment e")
    Double avgProgress();

    @Query("""
    SELECT SUM(c.price)
    FROM Enrollment e
    JOIN e.course c
    WHERE e.isCompleted = true
""")
    Double totalRevenue();




}
