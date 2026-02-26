package com.courses.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class UserDashboardDTO {

    private long totalEnrollments;
    private long completedCourses;
    private long inProgressCourses;

    private Double averageScore;
    private int passedTests;
    private int failedTests;

    private List<UserCourseProgressDTO> courseProgress;
    private List<UserRecentTestDTO> recentTests;
}