package nicos.enrollmentservice.domain;

import lombok.AllArgsConstructor;
import nicos.enrollmentservice.domain.exceptions.CourseAlreadyEnrolledException;
import nicos.enrollmentservice.domain.exceptions.CreditLimitExceededException;
import nicos.enrollmentservice.domain.exceptions.InvalidCreditLimitException;

import java.util.*;

public class Enrollment {
    private EnrollmentId enrollmentId;
    private StudentId studentId;
    private AcademicPeriodId academicPeriodId;
    private final List<EnrolledCourse> courses;
    private int maxAllowedCredits;

    private Enrollment(EnrollmentId enrollmentId,
                       StudentId studentId,
                       AcademicPeriodId academicPeriodId,
                       List<EnrolledCourse> courses,
                       int maxAllowedCredits) {
        this.enrollmentId = enrollmentId;
        this.studentId = studentId;
        this.academicPeriodId = academicPeriodId;
        this.courses = courses;
        this.maxAllowedCredits = maxAllowedCredits;
    }

    public static Enrollment create(StudentId studentId, AcademicPeriodId academicPeriodId) {
        Objects.requireNonNull(studentId, "The student id can't be null");
        Objects.requireNonNull(academicPeriodId, "The academic period id can't be null");

        EnrollmentId enrollmentId = new EnrollmentId(UUID.randomUUID());

        return new Enrollment(enrollmentId, studentId, academicPeriodId, new ArrayList<>(), 15 );
    }

    public void enrollCourse(CourseId courseId, int credits) {
        EnrolledCourse enrolledCourse = new EnrolledCourse(courseId, credits);

        boolean alreadyEnrolled = courses.stream()
                .anyMatch(course -> course.courseId().equals(courseId));

        if (alreadyEnrolled) {
            throw new CourseAlreadyEnrolledException();
        }

        int totalCredits = courses.stream().mapToInt(EnrolledCourse::credits).sum();

        if (totalCredits + credits > this.maxAllowedCredits) {
            throw new CreditLimitExceededException(maxAllowedCredits);
        }

        courses.add(enrolledCourse);
    }

    public void approveCreditExtension(int newLimit) {
        if (newLimit <= 15 || newLimit > 20) {
            throw new InvalidCreditLimitException();
        }

        this.maxAllowedCredits = newLimit;
    }

    public List<EnrolledCourse> courses() {
        return Collections.unmodifiableList(this.courses);
    }
}
