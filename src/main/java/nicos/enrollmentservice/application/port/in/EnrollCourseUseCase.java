package nicos.enrollmentservice.application.port.in;

import nicos.enrollmentservice.domain.EnrollmentId;

public interface EnrollCourseUseCase {
    EnrollmentId enroll(EnrollCourseCommand command);
}
