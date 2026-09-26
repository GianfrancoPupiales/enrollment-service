package nicos.enrollmentservice.infraestructure.adapter.out.persistence.mapper;

import nicos.enrollmentservice.domain.*;
import nicos.enrollmentservice.infraestructure.adapter.out.persistence.EnrollmentJpaEntity;

import java.util.List;

public class EnrollmentMapper {
    public Enrollment toDomain(EnrollmentJpaEntity entity) {
        EnrollmentId enrollmentId = new EnrollmentId(entity.getId());
        StudentId studentId = new StudentId(entity.getStudentId());
        AcademicPeriodId academicPeriodId = new AcademicPeriodId(entity.getAcademicPeriodId());
        int maxAllowedCredits = entity.getMaxAllowedCredits();

        List<EnrolledCourse> domainCourses = entity.getCourses().stream()
                .map(enrolledCourseEntity ->
                        new EnrolledCourse(new CourseId(enrolledCourseEntity.getCourseId()), enrolledCourseEntity.getCredits()))
                .toList();

        return Enrollment.reconstitute(enrollmentId, studentId, academicPeriodId, domainCourses, maxAllowedCredits);
    }

    public EnrollmentJpaEntity toJpaEntity(Enrollment enrollment){

    }
}
