package esgi.al.cleancode.project.Super_Cards.domain.exceptions;

import java.util.UUID;

public class RoundException extends RuntimeException {

    public RoundException() {
    }

    public RoundException(String message) {
        super(message);
    }

    public static RoundException create() {
        throw new RoundException();
    }

    public static RoundException notFoundRound(UUID id) {
        return new RoundException(String.format("Round with Id = " + id.toString() +" not found !"));
    }
}