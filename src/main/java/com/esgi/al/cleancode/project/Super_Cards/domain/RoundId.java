package com.esgi.al.cleancode.project.Super_Cards.domain;

import java.util.Objects;
import java.util.UUID;

public class RoundId {

    private final UUID value;

    private RoundId(UUID value) {
        this.value = value;
    }

    public static RoundId of(UUID value) {
        return new RoundId(value);
    }

    public String value() {
        return value.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoundId roundId = (RoundId) o;
        return Objects.equals(value, roundId.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return "RoundId{" +
                "value=" + value +
                '}';
    }
}
