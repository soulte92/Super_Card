package com.esgi.al.cleancode.project.Super_Cards.domain;

import java.util.Objects;
import java.util.UUID;

public class SessionId {

    private final UUID value;

    private SessionId(UUID value) {
        this.value = value;
    }

    public static SessionId of(UUID value) {
        return new SessionId(value);
    }

    public String value() {
        return value.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SessionId sessionId = (SessionId) o;
        return Objects.equals(value, sessionId.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return "SessionId{" +
                "value=" + value +
                '}';
    }
}
