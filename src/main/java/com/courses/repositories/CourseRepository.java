package com.courses.repositories;

import com.courses.models.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CourseRepository extends JpaRepository<Course, Integer>,
        JpaSpecificationExecutor<Course> {
    Page<Course> findByTitleContainingIgnoreCaseAndActiveTrue(
            String title,
            Pageable pageable
    );
}
