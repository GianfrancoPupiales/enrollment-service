package nicos.enrollmentservice.domain.exceptions;

public class CreditLimitExceededException extends RuntimeException {
    public CreditLimitExceededException(int maxAllowedCredits) {
        super(String.format("You only have %d credits", maxAllowedCredits));
    }
}
