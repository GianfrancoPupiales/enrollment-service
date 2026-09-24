package nicos.enrollmentservice.application.port.in;

import nicos.enrollmentservice.domain.AcademicPeriodId;
import nicos.enrollmentservice.domain.CourseId;
import nicos.enrollmentservice.domain.StudentId;

public record EnrollCourseCommand(
        StudentId studentId,
        AcademicPeriodId academicPeriodId,
        CourseId courseId,
        int credits
) {
}
