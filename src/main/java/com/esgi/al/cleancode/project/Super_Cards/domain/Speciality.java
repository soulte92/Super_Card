package com.esgi.al.cleancode.project.Super_Cards.domain;

import java.awt.*;

public enum Speciality {
    TANK("TANK"),
    KILLER("KILLER"),
    MAGICIAN("MAGICIAN");

    public final String label;

    Speciality(String label) {
        this.label = label;
    }
}
