package padroesestruturais.adapter.triplog;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FleetCloudAdapterTest extends TripLogContractTest {

    private FleetCloudClient client;

    @Override
    TripLog createTripLog() {
        client = new FleetCloudClient();
        return new FleetCloudAdapter(client, "my-fleet");
    }

    @Test
    void shouldSendTripToConfiguredFleet() {
        tripLog.save("trip-1", "Juiz de Fora - Rio");

        assertEquals("Juiz de Fora - Rio", new String(client.fetchTrip("my-fleet", "trip-1")));
        assertNull(client.fetchTrip("other-fleet", "trip-1"));
    }
}
