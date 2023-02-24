package esgi.al.cleancode.project.Super_Cards.domain.functional.enums;

public enum Winner {
    FIRSTPLAYERHERO("FIRSTPLAYERHERO"),
    SECONDPLAYERHERO("SECONDPLAYERHERO"),
    MATCH_NULL("MATCH_NULL");

    public final String label;

    Winner(String label) {
        this.label = label;
    }
}
