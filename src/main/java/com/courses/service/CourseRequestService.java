package com.courses.service;

import com.courses.dto.ChangeRequestStatusDTO;
import com.courses.dto.ResponseDTO;
import com.courses.models.Course;
import com.courses.models.CourseRequest;
import com.courses.models.enums.RequestStatus;

public interface CourseRequestService {

    CourseRequest getCourseRequestById(int id);
    ResponseDTO requestCourse(Course course, String email);
    boolean isUserRequestedCourse(Course course, String email);
    CourseRequest changeCourseRequestStatus(int courseRequestId, RequestStatus requestStatus);

    ResponseDTO changeRequestStatus(ChangeRequestStatusDTO changeRequestStatusDTO);

}
