package com.esgi.al.cleancode.project.Super_Cards.domain;

import java.util.Objects;
import java.util.UUID;

public class DeckId {
    private final UUID value;

    private DeckId(UUID value) {
        this.value = value;
    }

    public static DeckId of(UUID value) {
        return new DeckId(value);
    }

    public String value() {
        return value.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DeckId deckId = (DeckId) o;
        return Objects.equals(value, deckId.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return "DeckId{" +
                "value=" + value +
                '}';
    }
}
