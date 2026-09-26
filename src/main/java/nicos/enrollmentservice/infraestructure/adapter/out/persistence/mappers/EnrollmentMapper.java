package nicos.enrollmentservice.infraestructure.adapter.out.persistence.mappers;

import nicos.enrollmentservice.domain.*;
import nicos.enrollmentservice.infraestructure.adapter.out.persistence.entities.EnrolledCourseJpaEntity;
import nicos.enrollmentservice.infraestructure.adapter.out.persistence.entities.EnrollmentJpaEntity;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

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

    public EnrollmentJpaEntity toJpaEntity(Enrollment domain){
        EnrollmentJpaEntity entity = new EnrollmentJpaEntity();
        entity.setId(domain.getEnrollmentId().value());
        entity.setStudentId(domain.getStudentId().value());
        entity.setAcademicPeriodId(domain.getAcademicPeriodId().value());
        entity.setMaxAllowedCredits(domain.getMaxAllowedCredits());

        List<EnrolledCourseJpaEntity> courseEntities = domain.getCourses().stream()
                .map(domainCourse ->{
                    EnrolledCourseJpaEntity courseEntity = new EnrolledCourseJpaEntity();
                    courseEntity.setId(UUID.randomUUID());
                    courseEntity.setCourseId(domainCourse.courseId().value());
                    courseEntity.setCredits(domainCourse.credits());
                    courseEntity.setEnrollment(entity);
                    return courseEntity;
                })
                .collect(Collectors.toList());

        entity.setCourses(courseEntities);
        return entity;
    }
}
