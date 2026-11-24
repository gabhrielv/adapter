package padroesestruturais.adapter.triplog;

import java.util.HashMap;
import java.util.Map;

public class OnboardMemory {

    private final Map<String, String> values = new HashMap<>();

    public void set(String trip, String value) {
        values.put(trip, value);
    }

    public String get(String trip) {
        return values.get(trip);
    }
}
