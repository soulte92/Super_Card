package com.esgi.al.cleancode.project.Super_Cards.application.service;

import com.esgi.al.cleancode.project.Super_Cards.domain.HeroId;

public class HeroApplicationException extends RuntimeException {

    private HeroApplicationException() {
    }

    private HeroApplicationException(String message) {
        super(message);
    }

    public static HeroApplicationException create() {
        throw new HeroApplicationException();
    }


    public static HeroApplicationException notFoundHero(HeroId heroId) {
        return new HeroApplicationException(String.format("Hero with Id = %s not found !" + heroId.value()));
    }
}