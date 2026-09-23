package nicos.enrollmentservice.domain.exceptions;

public class NotEnrolledCourseException extends RuntimeException{
    public NotEnrolledCourseException(String value){
        super(String.format("The course %d is not enrolled", value));
    }
}
