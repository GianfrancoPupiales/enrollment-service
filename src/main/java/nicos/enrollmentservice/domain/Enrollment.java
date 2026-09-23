package nicos.enrollmentservice.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import nicos.enrollmentservice.domain.exceptions.CourseAlreadyEnrolledException;
import nicos.enrollmentservice.domain.exceptions.CreditLimitExceededException;
import nicos.enrollmentservice.domain.exceptions.InvalidCreditLimitException;
import nicos.enrollmentservice.domain.exceptions.NotEnrolledCourseException;

import java.util.*;

public class Enrollment {
    @Getter
    private EnrollmentId enrollmentId;
    @Getter
    private StudentId studentId;
    @Getter
    private AcademicPeriodId academicPeriodId;
    private final List<EnrolledCourse> courses;
    @Getter
    private int maxAllowedCredits;

    private Enrollment(
            EnrollmentId enrollmentId,
            StudentId studentId,
            AcademicPeriodId academicPeriodId,
            List<EnrolledCourse> courses,
            int maxAllowedCredits
    ) {
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

        return new Enrollment(enrollmentId, studentId, academicPeriodId, new ArrayList<>(), 15);
    }

    public static Enrollment reconstitute(
            EnrollmentId enrollmentId,
            StudentId studentId,
            AcademicPeriodId academicPeriodId,
            List<EnrolledCourse> courses,
            int maxAllowedCredits
    ) {
        return new Enrollment(
                enrollmentId,
                studentId,
                academicPeriodId,
                new ArrayList<>(courses),
                maxAllowedCredits
        );
    }

    public void enrollCourse(CourseId courseId, int credits) {
        EnrolledCourse enrolledCourse = new EnrolledCourse(courseId, credits);

        if (isAlreadyEnrolled(courseId)) {
            throw new CourseAlreadyEnrolledException();
        }

        if (totalCredits() + credits > this.maxAllowedCredits) {
            throw new CreditLimitExceededException(maxAllowedCredits);
        }

        courses.add(enrolledCourse);
    }

    private boolean isAlreadyEnrolled(CourseId courseId) {
        return courses.stream()
                .anyMatch(course -> course.courseId().equals(courseId));
    }

    public int totalCredits() {
        return courses.stream().mapToInt(EnrolledCourse::credits).sum();
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

    public void dropCourse(CourseId courseId){
        if (!isAlreadyEnrolled(courseId)){
            throw new NotEnrolledCourseException(courseId.value());
        }

        courses.removeIf(
                course -> course.courseId().equals(courseId)
        );
    }
}
