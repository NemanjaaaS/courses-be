package com.courses.service.implementation;

import com.courses.common.service.JwtService;
import com.courses.dto.UserResultsDTO;
import com.courses.dto.UserTableDTO;
import com.courses.exception.NotFoundException;
import com.courses.models.EnrolledTest;
import com.courses.models.Enrollment;
import com.courses.models.User;
import com.courses.models.enums.Role;
import com.courses.repositories.UserRepository;
import com.courses.service.EnrollmentService;
import com.courses.service.TestService;
import com.courses.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final EnrollmentService enrollmentService;
    private final TestService testService;

    @Override
    public int getUserIdByEmail(String email) {
        return getUserByEmail(email).getId();
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("User not found!"));
    }

    @Override
    public User getUserByToken(String token) {
        return getUserByEmail(jwtService.getEmailFromUnsplitToken(token));
    }

    @Override
    public List<UserTableDTO> getAllUsersForTable() {

        List<User> users = userRepository.findByRole(Role.USER);

        return users.stream().map(user -> {

            // Kursevi
            List<Enrollment> enrollments = enrollmentService.getAllUserEnrollments(user);

            int enrolledCourses = enrollments.size();

            int completedCourses = (int) enrollments.stream()
                    .filter(Enrollment::getIsCompleted)
                    .count();

            // Testovi
            List<UserResultsDTO> userTests = testService
                    .getAllUserTestsForAdmin()
                    .stream()
                    .filter(t -> t.getUser().getId() == user.getId())
                    .toList();

            int passedTests = (int) userTests.stream()
                    .filter(UserResultsDTO::isPassed)
                    .count();

            int failedTests = (int) userTests.stream()
                    .filter(t -> !t.isPassed())
                    .count();

            // DTO
            UserTableDTO dto = new UserTableDTO();
            dto.setId(user.getId());
            dto.setFirstName(user.getFirstName());
            dto.setLastName(user.getLastName());
            dto.setEmail(user.getEmail());
            dto.setEnrolledCourses(enrolledCourses);
            dto.setCompletedCourses(completedCourses);
            dto.setPassedTests(passedTests);
            dto.setFailedTests(failedTests);

            return dto;

        }).toList();
    }
}
