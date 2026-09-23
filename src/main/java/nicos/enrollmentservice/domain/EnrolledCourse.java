package nicos.enrollmentservice.domain;

import java.util.Objects;

public record EnrolledCourse (CourseId courseId, int credits){
    public EnrolledCourse {
        if(credits <= 0){
            throw new IllegalArgumentException("The credits must be positive");
        }
        Objects.requireNonNull(courseId, "CourseId must not be null");
    }
}
