package com.esgi.al.cleancode.project.Super_Cards.domain;

public class RoundException extends RuntimeException {

    private RoundException() {
    }

    private RoundException(String message) {
        super(message);
    }

    public static RoundException create() {
        throw new RoundException();
    }

    public static RoundException notFoundPlayer(RoundId roundId) {
        return new RoundException(String.format("Round with Id =  " + roundId.value() + " not found !"));
    }
}
