package nicos.enrollmentservice.domain.exceptions;

public class InvalidCreditLimitException extends RuntimeException{
    public InvalidCreditLimitException(){
        super("The credits must be between 15 and 20");
    }
}
