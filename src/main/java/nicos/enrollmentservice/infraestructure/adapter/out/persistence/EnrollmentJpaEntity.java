package nicos.enrollmentservice.infraestructure.adapter.out.persistence;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "enrollment")
public class EnrollmentJpaEntity {
    @Id
    @Column(name = "enrollment_id")
    private UUID id;
    @Column(name = "student_id", nullable = false)
    private String studentId;
    @Column(name = "academic_period_id", nullable = false)
    private String academicPeriodId;

    @Column(name = "max_allowed_credits", nullable = false)
    private int maxAllowedCredits;

    @OneToMany(mappedBy = "enrollment", cascade = CascadeType.ALL)
    private List<EnrolledCourseJpaEntity> courses = new ArrayList<>();
}
