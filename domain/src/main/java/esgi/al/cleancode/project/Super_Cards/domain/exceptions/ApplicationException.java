package esgi.al.cleancode.project.Super_Cards.domain.exceptions;

import java.util.UUID;

public class ApplicationException extends RuntimeException {

    public ApplicationException() {
    }

    public ApplicationException(String message) {
        super(message);
    }

    public static ApplicationException create() {
        throw new ApplicationException();
    }

}