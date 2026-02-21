package com.courses.service;

import com.courses.dto.ChangeRequestStatusDTO;
import com.courses.dto.ResponseDTO;
import com.courses.models.Course;
import com.courses.models.CourseRequest;
import com.courses.models.enums.RequestStatus;

import java.util.List;

public interface CourseRequestService {

    CourseRequest getCourseRequestById(int id);
    ResponseDTO requestCourse(Course course, String email);
    boolean isUserRequestedCourse(Course course, String email);
    CourseRequest changeCourseRequestStatus(int courseRequestId, RequestStatus requestStatus);
    List<CourseRequest> getRequestedCourses(String token);
    ResponseDTO changeRequestStatus(ChangeRequestStatusDTO changeRequestStatusDTO);

}
