package nicos.enrollmentservice.infraestructure.adapter.out.persistence.repositories;

import nicos.enrollmentservice.infraestructure.adapter.out.persistence.entities.EnrollmentJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface SpringDataEnrollmentRepository extends JpaRepository<EnrollmentJpaEntity, UUID> {
    Optional<EnrollmentJpaEntity> findByStudentIdAndAcademicPeriodId(String studentId, String academicPeriodId);
}
