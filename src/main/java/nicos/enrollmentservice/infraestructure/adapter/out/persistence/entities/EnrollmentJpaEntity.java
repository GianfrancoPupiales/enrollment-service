package nicos.enrollmentservice.infraestructure.adapter.out.persistence.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
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

    @OneToMany(mappedBy = "enrollment", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<EnrolledCourseJpaEntity> courses = new ArrayList<>();
}
