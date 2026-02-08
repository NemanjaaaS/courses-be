package com.courses.repositories;

import com.courses.models.Course;
import com.courses.models.CourseRequest;
import com.courses.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CourseRequestRepository extends JpaRepository<CourseRequest, Integer> {

    Optional<CourseRequest> findByCourseAndUser(Course course, User user);

}
