package com.esgi.al.cleancode.project.Super_Cards.domain;

public class HeroException extends RuntimeException {

    private HeroException() {
    }

    private HeroException(String message) {
        super(message);
    }

    public static HeroException create() {
        throw new HeroException();
    }

    public static HeroException enhaceCaracteriticsByPerCentException(double perCent){
        return new HeroException(String.format("Percent %f must be positive", perCent));
    }

    public static HeroException notFoundHero(HeroId heroId) {
        return new HeroException(String.format("Hero with Id = %s not found !" + heroId.value()));
    }
}