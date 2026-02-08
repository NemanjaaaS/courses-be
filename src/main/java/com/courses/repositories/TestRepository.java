package com.courses.repositories;

import com.courses.models.Course;
import com.courses.models.Test;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TestRepository extends JpaRepository<Test, Integer> {

    List<Test> getTestByCourse(Course course);
    Integer countByCourse(Course course);

}
