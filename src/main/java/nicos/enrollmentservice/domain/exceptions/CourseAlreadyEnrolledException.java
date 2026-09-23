package nicos.enrollmentservice.domain.exceptions;

public class CourseAlreadyEnrolledException extends RuntimeException{
    public CourseAlreadyEnrolledException(){
        super("The course is already enrolled");
    }
}
