package nicos.enrollmentservice.infraestructure.adapter.out.persistence.adapters;

import lombok.AllArgsConstructor;
import nicos.enrollmentservice.domain.AcademicPeriodId;
import nicos.enrollmentservice.domain.Enrollment;
import nicos.enrollmentservice.domain.EnrollmentId;
import nicos.enrollmentservice.domain.StudentId;
import nicos.enrollmentservice.domain.port.out.EnrollmentRepository;
import nicos.enrollmentservice.infraestructure.adapter.out.persistence.entities.EnrollmentJpaEntity;
import nicos.enrollmentservice.infraestructure.adapter.out.persistence.mappers.EnrollmentMapper;
import nicos.enrollmentservice.infraestructure.adapter.out.persistence.repositories.SpringDataEnrollmentRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class EnrollmentPersistenceAdapter implements EnrollmentRepository {

    private final SpringDataEnrollmentRepository springDataRepository;
    private final EnrollmentMapper mapper;

    public EnrollmentPersistenceAdapter(SpringDataEnrollmentRepository springDataRepository, EnrollmentMapper mapper) {
        this.springDataRepository = springDataRepository;
        this.mapper = mapper;
    }

    @Override
    public Enrollment save(Enrollment enrollment) {
        EnrollmentJpaEntity jpaEntity = mapper.toJpaEntity(enrollment);
        EnrollmentJpaEntity savedEntity = springDataRepository.save(jpaEntity);
        return mapper.toDomain(savedEntity);
    }

    @Override
    public Optional<Enrollment> findById(EnrollmentId enrollmentId) {
        return springDataRepository.findById(enrollmentId.value()).map(mapper::toDomain);
    }

    @Override
    public Optional<Enrollment> findByStudentIdAndAcademicPeriodId(StudentId studentId, AcademicPeriodId academicPeriodId) {
        return Optional.empty();
    }
}
