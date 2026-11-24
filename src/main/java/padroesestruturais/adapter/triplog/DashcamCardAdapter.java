package padroesestruturais.adapter.triplog;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.util.NoSuchElementException;

public class DashcamCardAdapter implements TripLog {

    private final DashcamCardDriver driver;
    private final Path baseDir;

    public DashcamCardAdapter(DashcamCardDriver driver, Path baseDir) {
        this.driver = driver;
        this.baseDir = baseDir.toAbsolutePath().normalize();
    }

    @Override
    public void save(String trip, String route) {
        try {
            driver.writeFile(resolve(trip), route.getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    @Override
    public String load(String trip) {
        try {
            return new String(driver.readFile(resolve(trip)), StandardCharsets.UTF_8);
        } catch (NoSuchFileException e) {
            throw new NoSuchElementException("Trip not found: " + trip);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    private String resolve(String trip) {
        Path file = baseDir.resolve(trip).normalize();
        if (!file.startsWith(baseDir) || file.equals(baseDir)) {
            throw new IllegalArgumentException("Invalid trip: " + trip);
        }
        return file.toString();
    }
}
