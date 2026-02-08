package com.courses.dto;

import com.courses.models.enums.RequestStatus;
import lombok.Data;

@Data
public class ChangeRequestStatusDTO {

    private int requestId;
    private RequestStatus requestStatus;

}
