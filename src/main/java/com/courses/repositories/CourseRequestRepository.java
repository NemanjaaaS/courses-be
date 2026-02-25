package com.courses.repositories;

import com.courses.models.Course;
import com.courses.models.CourseRequest;
import com.courses.models.User;
import com.courses.models.enums.RequestStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CourseRequestRepository extends JpaRepository<CourseRequest, Integer> {

    Optional<CourseRequest> findByCourseAndUser(Course course, User user);

    List<CourseRequest> findAllByStatus(RequestStatus status);

    @Query("SELECT COUNT(r) FROM CourseRequest r WHERE r.status = 'PENDING'")
    Long countPending();

    @Query("SELECT COUNT(r) FROM CourseRequest r WHERE r.status = 'APPROVED'")
    Long countApproved();

    @Query("SELECT COUNT(r) FROM CourseRequest r WHERE r.status = 'REJECTED'")
    Long countRejected();

}
