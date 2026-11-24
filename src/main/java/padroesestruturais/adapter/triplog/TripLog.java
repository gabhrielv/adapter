package padroesestruturais.adapter.triplog;

public interface TripLog {
    void save(String trip, String route);
    String load(String trip);
}
