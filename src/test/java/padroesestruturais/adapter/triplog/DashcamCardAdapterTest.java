package padroesestruturais.adapter.triplog;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

class DashcamCardAdapterTest extends TripLogContractTest {

    @TempDir
    Path tempDir;

    @Override
    TripLog createTripLog() {
        return new DashcamCardAdapter(new DashcamCardDriver(), tempDir);
    }

    @Test
    void shouldWriteFileInsideBaseDir() throws Exception {
        tripLog.save("trip-1", "Juiz de Fora - Rio");

        assertEquals("Juiz de Fora - Rio", new String(Files.readAllBytes(tempDir.resolve("trip-1"))));
    }

    @Test
    void shouldRejectTripOutsideBaseDir() {
        assertThrows(IllegalArgumentException.class, () -> tripLog.save("../evil", "x"));
        assertThrows(IllegalArgumentException.class, () -> tripLog.load("../evil"));
    }
}
