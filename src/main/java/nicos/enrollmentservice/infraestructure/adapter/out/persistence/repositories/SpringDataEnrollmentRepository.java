package nicos.enrollmentservice.infraestructure.adapter.out.persistence.repositories;

import nicos.enrollmentservice.infraestructure.adapter.out.persistence.entities.EnrollmentJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SpringDataEnrollmentRepository extends JpaRepository<EnrollmentJpaEntity, UUID> {
}
