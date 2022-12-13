package com.esgi.al.cleancode.project.Super_Cards.domain;

import java.util.Objects;
import java.util.UUID;

public class PlayerId {

    private final UUID value;

    private PlayerId(UUID value) {
        this.value = value;
    }

    public static PlayerId of(UUID value) {
        return new PlayerId(value);
    }

    public String value() {
        return value.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlayerId playerId = (PlayerId) o;
        return Objects.equals(value, playerId.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return "PlayerId{" +
                "value=" + value +
                '}';
    }
}
