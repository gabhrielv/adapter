package padroesestruturais.adapter.triplog;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

abstract class TripLogContractTest {

    protected TripLog tripLog;

    abstract TripLog createTripLog();

    @BeforeEach
    void setUp() {
        tripLog = createTripLog();
    }

    @Test
    void shouldLoadWhatWasSaved() {
        tripLog.save("trip-1", "Juiz de Fora - Rio");

        assertEquals("Juiz de Fora - Rio", tripLog.load("trip-1"));
    }

    @Test
    void shouldOverwriteExistingTrip() {
        tripLog.save("trip-1", "old");
        tripLog.save("trip-1", "new");

        assertEquals("new", tripLog.load("trip-1"));
    }

    @Test
    void shouldKeepTripsIndependent() {
        tripLog.save("a", "1");
        tripLog.save("b", "2");

        assertEquals("1", tripLog.load("a"));
        assertEquals("2", tripLog.load("b"));
    }

    @Test
    void shouldPreserveSpecialCharacters() {
        tripLog.save("trip-1", "São Paulo → Belo Horizonte ✓");

        assertEquals("São Paulo → Belo Horizonte ✓", tripLog.load("trip-1"));
    }

    @Test
    void shouldFailWhenTripDoesNotExist() {
        assertThrows(NoSuchElementException.class, () -> tripLog.load("missing"));
    }
}
