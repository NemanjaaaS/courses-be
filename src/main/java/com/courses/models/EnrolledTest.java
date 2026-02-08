package com.courses.models;

import com.courses.dto.TestPassDTO;
import com.courses.models.keys.EnrolledTestKey;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Entity
@Table(name = "enrolled_tests")
@Data
@AllArgsConstructor
@NoArgsConstructor
@IdClass(EnrolledTestKey.class)
public class EnrolledTest {

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "test_id")
    private Test test;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    private boolean isPassed;

    private int percentage;

    private Timestamp createdAt;

    private Timestamp updatedAt;

    public EnrolledTest(Test test, User user, TestPassDTO testPassDTO) {
        this.test = test;
        this.user = user;
        this.isPassed = testPassDTO.isPassed();
        this.percentage = testPassDTO.getPercentage();
    }

    @PrePersist
    protected void onCreate() {
        this.createdAt = new Timestamp(System.currentTimeMillis());
        this.updatedAt = new Timestamp(System.currentTimeMillis());
    }

}
