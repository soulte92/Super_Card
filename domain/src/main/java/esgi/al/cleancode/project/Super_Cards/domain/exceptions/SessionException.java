package esgi.al.cleancode.project.Super_Cards.domain.exceptions;

import java.util.UUID;

public class SessionException extends RuntimeException {

    public SessionException() {
    }

    public SessionException(String message) {
        super(message);
    }

    public static SessionException create() {
        throw new SessionException();
    }

    public static SessionException notFoundSession(UUID id) {
        return new SessionException(String.format("Session with Id = " + id.toString() +" not found !"));
    }
}