package com.courses.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DashboardDTO {
    private long totalUsers;
    private long activeUsers;
    private long totalCourses;
    private double totalRevenue;
    private double pendingRevenue;
    private double conversionRate;
    private List<MonthlyRevenueDTO> revenueByMonth;
    private Double passRate;
    private List<AverageTestScoreDTO> averageTestScoreList;
    private int passedTests;
    private int failedTests;
    private List<CumulativeUserCountDTO> cumulativeUserCount;
    private List<TopCourseDTO> topCourseDTOS;
}
