package com.courses.dto;

import com.courses.models.Test;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AverageTestScoreDTO {

    private Test tests;
    private Double averageScore;

}
