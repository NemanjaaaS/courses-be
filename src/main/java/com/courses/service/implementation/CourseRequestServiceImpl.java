package com.courses.service.implementation;

import com.courses.dto.ChangeRequestStatusDTO;
import com.courses.dto.CourseTableDTO;
import com.courses.dto.ResponseDTO;
import com.courses.exception.BadRequestException;
import com.courses.exception.NotFoundException;
import com.courses.models.Course;
import com.courses.models.CourseRequest;
import com.courses.models.User;
import com.courses.models.enums.RequestStatus;
import com.courses.repositories.CourseRequestRepository;
import com.courses.service.CourseRequestService;
import com.courses.service.EnrollmentService;
import com.courses.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CourseRequestServiceImpl implements CourseRequestService {

    private final CourseRequestRepository courseRequestRepository;
    private final UserService userService;
    private final EnrollmentService enrollmentService;

    public CourseRequest getCourseRequestById(int id) {
        return courseRequestRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Course request not found!"));
    }

    @Override
    public ResponseDTO requestCourse(Course course, String email) {
        User user = userService.getUserByEmail(email);
        Optional<CourseRequest> courseRequestOptional = courseRequestRepository.findByCourseAndUser(course, user);

        if (courseRequestOptional.isPresent()) {
            throw new BadRequestException("You already requested course. Your request status: " +
                    courseRequestOptional.get().getStatus());
        }

        courseRequestRepository.save(new CourseRequest(course, user));

        return new ResponseDTO("Course requested successfully");
    }

    @Override
    public boolean isUserRequestedCourse(Course course, String email) {
        return courseRequestRepository.findByCourseAndUser(course, userService.getUserByEmail(email)).isPresent();
    }

    public CourseRequest changeCourseRequestStatus(int courseRequestId, RequestStatus requestStatus) {
        CourseRequest courseRequest = getCourseRequestById(courseRequestId);
        courseRequest.setStatus(requestStatus);
        return courseRequestRepository.save(courseRequest);
    }

    public ResponseDTO changeRequestStatus(ChangeRequestStatusDTO changeRequestStatusDTO) {
        CourseRequest courseRequest = getCourseRequestById(changeRequestStatusDTO.getRequestId());

        if (courseRequest.getStatus().equals(RequestStatus.APPROVED)) {
            return new ResponseDTO("Course status can't be changed from APPROVED");
        }

        if (changeRequestStatusDTO.getRequestStatus().equals(RequestStatus.APPROVED)) {
            enrollmentService.createEnrollment(courseRequest.getUser(), courseRequest.getCourse());
        }

        courseRequest.updateAsAdmin(changeRequestStatusDTO.getRequestStatus());

        courseRequestRepository.save(courseRequest);

        return new ResponseDTO("You changed status to: " + changeRequestStatusDTO.getRequestStatus());
    }

    @Override
    public List<CourseRequest> getRequestedCourses(String token){
        return courseRequestRepository.findAllCourseRequests();
    }

}
