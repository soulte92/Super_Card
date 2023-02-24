package esgi.al.cleancode.project.Super_Cards.domain.functional.enums;

import java.util.HashMap;

public class SpecialityGenerator {
    public static String generateRandomSpeciality() {
        HashMap<String, Double> specialityProbabilities = new HashMap<String, Double>();
        specialityProbabilities.put(Speciality.TANK.label, 0.33);
        specialityProbabilities.put(Speciality.MAGICIAN.label, 0.33);
        specialityProbabilities.put(Speciality.KILLER.label, 0.33);

        ProbalityDistribution distribution = new ProbalityDistribution(specialityProbabilities);

        return (String) distribution.sample();
    }
}
