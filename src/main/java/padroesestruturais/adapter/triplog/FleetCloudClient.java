package padroesestruturais.adapter.triplog;

import java.util.HashMap;
import java.util.Map;

public class FleetCloudClient {

    private final Map<String, byte[]> trips = new HashMap<>();

    public void sendTrip(String fleet, String trip, byte[] body) {
        trips.put(fleet + "/" + trip, body);
    }

    public byte[] fetchTrip(String fleet, String trip) {
        return trips.get(fleet + "/" + trip);
    }
}
