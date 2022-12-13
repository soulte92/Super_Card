package com.esgi.al.cleancode.project.Super_Cards.domain;

import java.util.Objects;
import java.util.UUID;

public class HeroId {

    private final UUID value;

    private HeroId(UUID value) {
        this.value = value;
    }

    public static HeroId of(UUID value) {
        return new HeroId(value);
    }

    public String value() {
        return value.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HeroId heroId = (HeroId) o;
        return Objects.equals(value, heroId.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return "HeroId{" +
                "value=" + value +
                '}';
    }
}
