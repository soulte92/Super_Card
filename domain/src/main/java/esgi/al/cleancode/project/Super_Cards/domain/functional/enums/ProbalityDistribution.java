package esgi.al.cleancode.project.Super_Cards.domain.functional.enums;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class ProbalityDistribution<T> {
    List<Double> probs = new ArrayList<>();
    List<T> events = new ArrayList<>();
    double sumProb;
    Random rand = new Random();

    public ProbalityDistribution(Map<T, Double> probs) {
        for (T event : probs.keySet()) {
            sumProb += probs.get(event);
            events.add(event);
            this.probs.add(probs.get(event));
        }
    }

    public T sample() {
        T value;
        double prob = rand.nextDouble() * sumProb;
        int i;
        for (i = 0; prob > 0; i++) {
            prob -= probs.get(i);
        }
        return events.get(i - 1);
    }
}