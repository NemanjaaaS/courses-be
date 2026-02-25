package com.courses.repositories;

import com.courses.models.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

public interface CourseRepository extends JpaRepository<Course, Integer>,
        JpaSpecificationExecutor<Course> {
    Page<Course> findByTitleContainingIgnoreCaseAndActiveTrue(
            String title,
            Pageable pageable
    );

    @Query("SELECT COUNT(c) FROM Course c")
    Long countAllCourses();

}
