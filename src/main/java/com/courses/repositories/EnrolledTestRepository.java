package com.courses.repositories;

import com.courses.models.EnrolledTest;
import com.courses.models.Test;
import com.courses.models.User;
import com.courses.models.keys.EnrolledTestKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface EnrolledTestRepository extends JpaRepository<EnrolledTest, EnrolledTestKey> {

    Boolean existsByUserAndTest(User user, Test test);
    @Query("SELECT e.isPassed " +
            "FROM EnrolledTest e " +
            "WHERE e.user = :user " +
            "AND e.test = :test")
    Boolean isUserPassed(User user, Test test);

    @Query(value = """
            SELECT COUNT(`tests`.`id`)
            FROM `enrolled_tests`
            JOIN `tests`
            ON `enrolled_tests`.`test_id` = `tests`.`id`
            JOIN `courses`
            ON `courses`.`id` = `tests`.`course_id`
            WHERE `courses`.`id` = :courseId
            AND `enrolled_tests`.`user_id` = :userId
            AND `enrolled_tests`.`is_passed` = 1;
            """, nativeQuery = true)
    Integer getPassedTestsCountForUser(int courseId, int userId);
}
