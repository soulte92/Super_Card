package esgi.al.cleancode.project.Super_Cards.domain.functional.enums;

public enum Rarety {
    COMMON("COMMON"),
    RARE("RARE"),
    LEGENDARY("LEGENDARY");

    public final String label;

    Rarety(String label) {
        this.label = label;
    }
}
