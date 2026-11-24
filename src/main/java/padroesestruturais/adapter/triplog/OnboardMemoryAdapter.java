package padroesestruturais.adapter.triplog;

import java.util.NoSuchElementException;

public class OnboardMemoryAdapter implements TripLog {

    private final OnboardMemory memory;

    public OnboardMemoryAdapter(OnboardMemory memory) {
        this.memory = memory;
    }

    @Override
    public void save(String trip, String route) {
        memory.set(trip, route);
    }

    @Override
    public String load(String trip) {
        String value = memory.get(trip);
        if (value == null) {
            throw new NoSuchElementException("Trip not found: " + trip);
        }
        return value;
    }
}
