package com.courses.specification;

import com.courses.models.Course;
import com.courses.models.enums.CourseCategory;
import com.courses.models.enums.CourseLevel;
import org.springframework.data.jpa.domain.Specification;

public class CourseSpecification {

    public static Specification<Course> titleContains(String title) {
        return (root, query, cb) ->
                title == null || title.isBlank()
                        ? null
                        : cb.like(
                        cb.lower(root.get("title")),
                        "%" + title.toLowerCase() + "%"
                );
    }

    public static Specification<Course> hasCategory(CourseCategory category) {
        return (root, query, cb) ->
                category == null
                        ? null
                        : cb.equal(root.get("category"), category);
    }

    public static Specification<Course> hasLevel(CourseLevel level) {
        return (root, query, cb) ->
                level == null
                        ? null
                        : cb.equal(root.get("level"), level);
    }

    public static Specification<Course> isActive() {
        return (root, query, cb) ->
                cb.isTrue(root.get("active"));
    }

}
