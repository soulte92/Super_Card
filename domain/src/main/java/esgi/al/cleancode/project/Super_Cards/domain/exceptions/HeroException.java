package esgi.al.cleancode.project.Super_Cards.domain.exceptions;

import esgi.al.cleancode.project.Super_Cards.domain.functional.enums.Rarety;
import esgi.al.cleancode.project.Super_Cards.domain.functional.enums.Speciality;

import java.util.UUID;

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

    public static HeroException notSupportedSpeciality(String speciality){
        return new HeroException(String.format("Speciality '%s' is not supported. Choose a speciality in {%s , %s, %s}",
                speciality, Speciality.TANK.label, Speciality.KILLER.label, Speciality.MAGICIAN.label));
    }
    public static HeroException notSupportedRarety(String speciality){
        return new HeroException(String.format("Rarety '%s' is not supported. Choose a rarety in {%s, %s, %s}",
                speciality, Rarety.COMMON.label, Rarety.RARE.label, Rarety.LEGENDARY.label));
    }

    public static HeroException notFoundHero(UUID id) {
        return new HeroException(String.format("Hero with Id = " + id.toString() +" not found !"));
    }
}