package esgi.al.cleancode.project.Super_Cards.domain.functional.enums;

public enum Speciality {
    TANK("TANK"),
    KILLER("KILLER"),
    MAGICIAN("MAGICIAN");

    public final String label;

    Speciality(String label) {
        this.label = label;
    }
}
