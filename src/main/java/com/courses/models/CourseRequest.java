package com.courses.models;

import com.courses.models.enums.RequestStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.sql.Timestamp;

@Entity
@Table(name = "course_requests")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourseRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;

    @Enumerated(EnumType.STRING)
    private RequestStatus status = RequestStatus.PENDING;

    private Timestamp requestDate;

    private Timestamp processedDate;

    public CourseRequest(Course course, User user) {
        this.course = course;
        this.user = user;
    }

    public void updateAsAdmin(RequestStatus requestStatus) {
        this.status = requestStatus;
        this.processedDate = new Timestamp(System.currentTimeMillis());
    }

    @PrePersist
    protected void onCreate() {
        this.requestDate = new Timestamp(System.currentTimeMillis());
    }
}