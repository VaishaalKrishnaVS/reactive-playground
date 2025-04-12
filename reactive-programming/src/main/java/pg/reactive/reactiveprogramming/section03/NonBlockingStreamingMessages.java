package pg.reactive.reactiveprogramming.section03;

import pg.reactive.reactiveprogramming.common.Util;
import pg.reactive.reactiveprogramming.section03.client.ExternalServiceClient;

import java.time.Duration;
/**
 * Demonstrates non-blocking streaming of messages using reactive programming.
 * This class shows how multiple subscribers can independently consume messages
 * from an external service client without blocking each other.
 */
public class NonBlockingStreamingMessages {
    /**
     * Main method that demonstrates non-blocking message streaming.
     * Creates two subscribers that independently consume messages from the external service.
     *
     * @param args Command line arguments (not used)
     */
    public static void main(String[] args) {
        // Create client instance to connect to external service
        var client = new ExternalServiceClient();

        // First subscriber consuming messages
        client.getNames()
                .subscribe(Util.subscriber("sub01"));

        // Second subscriber consuming messages independently
        client.getNames()
                .subscribe(Util.subscriber("sub02"));

        // Sleep to allow time for message processing
        Util.sleep(Duration.ofSeconds(10));

    }
}
