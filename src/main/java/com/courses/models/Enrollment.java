package com.courses.models;

import com.courses.models.keys.EnrollmentKey;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Entity
@Table(name = "enrollments")
@IdClass(EnrollmentKey.class)
@Data
@NoArgsConstructor
public class Enrollment {

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id")
    private Course course;

    private Timestamp enrolledAt;

    private Integer progressPercentage = 0;

    private Boolean isCompleted = false;

    private Timestamp completedAt;

    public Enrollment(User user, Course course) {
        this.user = user;
        this.course = course;
    }

    @PrePersist
    protected void onCreate() {
        this.enrolledAt = new Timestamp(System.currentTimeMillis());
    }

}
