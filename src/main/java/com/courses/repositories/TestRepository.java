package com.courses.repositories;

import com.courses.models.Course;
import com.courses.models.Test;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TestRepository extends JpaRepository<Test, Integer> {

    List<Test> getTestByCourse(Course course);
    Integer countByCourse(Course course);

    @Query("SELECT AVG(t.percentage) FROM EnrolledTest t")
    Double avgScore();

    @Query("""
        SELECT 
        (SUM(CASE WHEN t.isPassed = true THEN 1 ELSE 0 END) * 100.0 / COUNT(t))
        FROM EnrolledTest t
    """)
    Double passRate();

}
