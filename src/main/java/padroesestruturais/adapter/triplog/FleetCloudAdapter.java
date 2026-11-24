package padroesestruturais.adapter.triplog;

import java.nio.charset.StandardCharsets;
import java.util.NoSuchElementException;

public class FleetCloudAdapter implements TripLog {

    private final FleetCloudClient client;
    private final String fleet;

    public FleetCloudAdapter(FleetCloudClient client, String fleet) {
        this.client = client;
        this.fleet = fleet;
    }

    @Override
    public void save(String trip, String route) {
        client.sendTrip(fleet, trip, route.getBytes(StandardCharsets.UTF_8));
    }

    @Override
    public String load(String trip) {
        byte[] body = client.fetchTrip(fleet, trip);
        if (body == null) {
            throw new NoSuchElementException("Trip not found: " + trip);
        }
        return new String(body, StandardCharsets.UTF_8);
    }
}
