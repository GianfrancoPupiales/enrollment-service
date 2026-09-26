package nicos.enrollmentservice.infraestructure.adapter.out.persistence;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "enrolled_course")
public class EnrolledCourseJpaEntity {
    @Id
    @Column(name = "enrolled_course_id", nullable = false)
    private UUID id;

    @Column(name = "course_id")
    private String courseId;

    @Column(name = "credits", nullable = false)
    private int credits;

    @ManyToOne
    @JoinColumn(name = "enrollment_id")
    private EnrollmentJpaEntity enrollment;
}
