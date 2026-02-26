package com.courses.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserRecentTestDTO {

    private String courseName;
    private Double score;
    private boolean passed;
    private String status;
}