package com.courses.repositories;

import com.courses.dto.AverageTestScoreDTO;
import com.courses.models.EnrolledTest;
import com.courses.models.Test;
import com.courses.models.User;
import com.courses.models.keys.EnrolledTestKey;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


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

    @Query("SELECT AVG(t.percentage) FROM EnrolledTest t")
    Double avgScore();

    @Query("""
    SELECT 
    (SUM(CASE WHEN t.isPassed = true THEN 1 ELSE 0 END) * 100.0 / COUNT(t))
    FROM EnrolledTest t
""")
    Double passRate();

    @Query("""
    SELECT new com.courses.dto.AverageTestScoreDTO(et.test, AVG(et.percentage))
    FROM EnrolledTest et
    GROUP BY et.test
    ORDER BY AVG(et.percentage) DESC
    """)
    List<AverageTestScoreDTO> findAverageTestScoresOrderByScoreDesc();

    @Query("SELECT COUNT(et) FROM EnrolledTest et WHERE et.isPassed")
    int  getAllPassedTests();

    @Query("SELECT COUNT(et) FROM EnrolledTest et WHERE et.isPassed = false")
    int  getAllFailedTests();

    @Query("SELECT COUNT(e) FROM EnrolledTest e WHERE e.user.id = :userId AND e.isPassed = true")
    int countPassedByUser(int userId);

    @Query("SELECT COUNT(e) FROM EnrolledTest e WHERE e.user.id = :userId AND e.isPassed = false")
    int countFailedByUser(int userId);

    @Query("SELECT AVG(e.percentage) FROM EnrolledTest e WHERE e.user.id = :userId")
    Double averageScoreByUser(int userId);

    @Query("""
    SELECT COUNT(e)
    FROM EnrolledTest e
    WHERE e.user.id = :userId
    AND e.test.course.id = :courseId
    AND e.isPassed = true
""")
    long countPassedByUserAndCourse(int userId, Long courseId);

    @Query("""
    SELECT e FROM EnrolledTest e
    WHERE e.user.id = :userId
    ORDER BY e.createdAt DESC
""")
    List<EnrolledTest> findTop3ByUserOrderByDateDesc(int userId, Pageable pageable);
}
