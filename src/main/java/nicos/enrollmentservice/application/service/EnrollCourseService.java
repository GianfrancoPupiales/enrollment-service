package nicos.enrollmentservice.application.service;

import nicos.enrollmentservice.application.port.in.EnrollCourseCommand;
import nicos.enrollmentservice.application.port.in.EnrollCourseUseCase;
import nicos.enrollmentservice.domain.Enrollment;
import nicos.enrollmentservice.domain.EnrollmentId;
import nicos.enrollmentservice.domain.port.out.EnrollmentRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
@Transactional
public class EnrollCourseService implements EnrollCourseUseCase {
    private final EnrollmentRepository enrollmentRepository;

    public EnrollCourseService(EnrollmentRepository enrollmentRepository) {
        this.enrollmentRepository = enrollmentRepository;
    }

    @Override
    public EnrollmentId enroll(EnrollCourseCommand command) {
        Optional<Enrollment> enrollmentOpt = enrollmentRepository.findByStudentIdAndAcademicPeriodId(command.studentId(), command.academicPeriodId());
        Enrollment enrollment = enrollmentOpt.orElseGet(() -> Enrollment.create(command.studentId(), command.academicPeriodId()));
        enrollment.enrollCourse(command.courseId(), command.credits());
        enrollmentRepository.save(enrollment);

        return enrollment.getEnrollmentId();
    }
}
