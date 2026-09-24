package nicos.enrollmentservice.domain.port.out;

import nicos.enrollmentservice.domain.AcademicPeriodId;
import nicos.enrollmentservice.domain.Enrollment;
import nicos.enrollmentservice.domain.EnrollmentId;
import nicos.enrollmentservice.domain.StudentId;

import java.util.Optional;

public interface EnrollmentRepository {
    Enrollment save(Enrollment enrollment);
    Optional<Enrollment> findById(EnrollmentId enrollmentId);
    Optional<Enrollment> findByStudentIdAndAcademicPeriodId(StudentId studentId, AcademicPeriodId academicPeriodId);
}
