package com.courses.service.implementation;

import com.courses.dto.*;
import com.courses.models.CourseRequest;
import com.courses.models.Enrollment;
import com.courses.models.User;
import com.courses.models.enums.RequestStatus;
import com.courses.models.enums.Role;
import com.courses.repositories.*;
import com.courses.service.DashboardService;
import com.courses.service.EnrollmentService;
import com.courses.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DashboardServiceImpl implements DashboardService {
    private final UserRepository userRepository;
    private final CourseRepository courseRepository;
    private final CourseRequestRepository courseRequestRepository;
    private final EnrollmentService enrollmentService;
    private final TestRepository testRepository;
    private final EnrolledTestRepository enrolledTestRepository;
    private final UserService userService;

    @Override
    public DashboardDTO getAdminDashboard() {

        // ===== USERS =====
        long totalUsers = userRepository.countByRole(Role.USER);

        List<CumulativeUserCountDTO> cumulativeUserCount = userRepository.getCumulativeUserCountByRole();

        Double passRate = enrolledTestRepository.passRate();

        int passedTests = enrolledTestRepository.getAllPassedTests();

        int failedTests = enrolledTestRepository.getAllFailedTests();

        List<TopCourseDTO> topCourseDTOS = courseRepository.findTopCourses(PageRequest.of(0, 5));

        List<AverageTestScoreDTO> averageTestScoreList  = enrolledTestRepository.findAverageTestScoresOrderByScoreDesc();

        long activeUsers = userRepository.findAll().stream()
                .filter(user -> !enrollmentService.getAllUserEnrollments(user).isEmpty())
                .count();

        // ===== COURSES =====
        long totalCourses = courseRepository.count();

        // ===== REQUESTS =====
        List<CourseRequest> requests = courseRequestRepository.findAll();

        double totalRevenue = requests.stream()
                .filter(r -> r.getStatus() == RequestStatus.APPROVED)
                .mapToDouble(r -> r.getCourse().getPrice())
                .sum();

        double pendingRevenue = requests.stream()
                .filter(r -> r.getStatus() == RequestStatus.PENDING)
                .mapToDouble(r -> r.getCourse().getPrice())
                .sum();

        long approvedCount = requests.stream()
                .filter(r -> r.getStatus() == RequestStatus.APPROVED)
                .count();

        double conversionRate = requests.isEmpty()
                ? 0
                : ((double) approvedCount / requests.size()) * 100;

        // ===== REVENUE BY MONTH =====
        Map<String, Double> revenueMap = requests.stream()
                .filter(r -> r.getStatus() == RequestStatus.APPROVED)
                .collect(Collectors.groupingBy(
                        r -> {
                            Timestamp date = r.getProcessedDate();
                            LocalDate localDate = date.toLocalDateTime().toLocalDate();
                            return localDate.getYear() + "-" + localDate.getMonthValue();
                        },
                        Collectors.summingDouble(r -> r.getCourse().getPrice())
                ));

        List<MonthlyRevenueDTO> revenueByMonth = revenueMap.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .map(entry -> new MonthlyRevenueDTO(entry.getKey(), entry.getValue()))
                .toList();


        return new DashboardDTO(
                totalUsers,
                activeUsers,
                totalCourses,
                totalRevenue,
                pendingRevenue,
                conversionRate,
                revenueByMonth,
                passRate,
                averageTestScoreList,
                passedTests,
                failedTests,
                cumulativeUserCount,
                topCourseDTOS
        );
    }

    @Override
    public UserDashboardDTO getUserDashboard(String token) {

        User user = userService.getUserByToken(token);

        var enrollments = enrollmentService.getAllUserEnrollments(user);

        long totalEnrollments = enrollments.size();

        long completedCourses = enrollments.stream()
                .filter(Enrollment::getIsCompleted)
                .count();

        long inProgressCourses = totalEnrollments - completedCourses;

        int passedTests = enrolledTestRepository.countPassedByUser(user.getId());
        int failedTests = enrolledTestRepository.countFailedByUser(user.getId());

        Double averageScore = enrolledTestRepository.averageScoreByUser(user.getId());

        // ===== Course Progress =====
        var courseProgress = enrollments.stream()
                .map(e -> {

                    long totalTests = testRepository.countByCourse(e.getCourse());

                    long completedTests =
                            enrolledTestRepository.countPassedByUserAndCourse(
                                    user.getId(),
                                    e.getCourse().getId()
                            );

                    return new UserCourseProgressDTO(
                            e.getCourse().getTitle(),
                            completedTests,
                            totalTests
                    );
                }).toList();

        // ===== Recent Tests =====
        var recentTests =
                enrolledTestRepository.findTop3ByUserOrderByDateDesc(user.getId(), PageRequest.of(0, 5))
                        .stream()
                        .map(t -> new UserRecentTestDTO(
                                t.getTest().getCourse().getTitle(),
                                (double) t.getPercentage(),
                                t.isPassed(),
                                t.isPassed() ? "completed" : "failed"
                        ))
                        .toList();

        return new UserDashboardDTO(
                totalEnrollments,
                completedCourses,
                inProgressCourses,
                averageScore,
                passedTests,
                failedTests,
                courseProgress,
                recentTests
        );
    }
}
