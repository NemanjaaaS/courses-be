package com.courses.dto;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class CertificateDTO {

    private String courseName;
    private String userFullName;
    private Timestamp completionDate;

    public CertificateDTO(String courseName, String userFullName, Timestamp completionDate) {
        this.courseName = courseName;
        this.userFullName = userFullName;
        this.completionDate = completionDate;
    }

}
