package com.courses.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDate;

@Data
@AllArgsConstructor
public class CumulativeUserCountDTO {
    private LocalDate createdAt;
    private BigDecimal cumulativeTotal;
}
