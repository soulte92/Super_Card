package esgi.al.cleancode.project.Super_Cards.domain.exceptions;

import esgi.al.cleancode.project.Super_Cards.domain.functional.enums.Rarity;
import esgi.al.cleancode.project.Super_Cards.domain.functional.enums.Speciality;

import java.util.UUID;

public class PlayerException extends RuntimeException {

    public PlayerException() {
    }

    public PlayerException(String message) {
        super(message);
    }

    public static PlayerException create() {
        throw new PlayerException();
    }

    public static PlayerException notFoundPlayer(UUID id) {
        return new PlayerException(String.format("Player with Id = " + id.toString() +" not found !"));
    }
}