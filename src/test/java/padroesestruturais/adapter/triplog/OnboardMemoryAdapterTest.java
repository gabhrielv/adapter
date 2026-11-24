package padroesestruturais.adapter.triplog;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OnboardMemoryAdapterTest extends TripLogContractTest {

    private OnboardMemory memory;

    @Override
    TripLog createTripLog() {
        memory = new OnboardMemory();
        return new OnboardMemoryAdapter(memory);
    }

    @Test
    void shouldDelegateToOnboardMemory() {
        tripLog.save("trip-1", "Juiz de Fora - Rio");

        assertEquals("Juiz de Fora - Rio", memory.get("trip-1"));
    }
}
