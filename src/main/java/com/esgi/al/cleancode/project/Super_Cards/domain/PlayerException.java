package com.esgi.al.cleancode.project.Super_Cards.domain;

public class PlayerException extends RuntimeException {

    private PlayerException() {
    }

    private PlayerException(String message) {
        super(message);
    }

    public static PlayerException create() {
        throw new PlayerException();
    }

    public static PlayerException notFoundPlayer(PlayerId playerId) {
        return new PlayerException(String.format("Player with Id =  " + playerId.value() + " not found !"));
    }
}
