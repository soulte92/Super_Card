package esgi.al.cleancode.project.Super_Cards.domain.functional.enums;

public enum Rarity {
    COMMON("COMMON"),
    RARE("RARE"),
    LEGENDARY("LEGENDARY");

    public final String label;

    Rarity(String label) {
        this.label = label;
    }
}
