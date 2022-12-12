package com.esgi.al.cleancode.project.Super_Cards.domain;

public class HeroConfigurationException extends RuntimeException {

    private HeroConfigurationException() {
    }

    private HeroConfigurationException(String message) {
        super(message);
    }

    public static HeroConfigurationException create() {
        throw new HeroConfigurationException();
    }

    public static HeroConfigurationException notSupportedSpeciality(String speciality){
        return new HeroConfigurationException(String.format("Speciality %s is not supported", speciality));
    }

    public static HeroConfigurationException notSupportedRarety(String rarety){
        return new HeroConfigurationException(String.format("Rarety %s is not supported", rarety));
    }

}