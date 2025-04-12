package pg.reactive.reactiveprogramming.section03;

import pg.reactive.reactiveprogramming.common.Util;
import reactor.core.publisher.Flux;

import java.util.List;
/**
 * Demonstrates the creation and usage of a Flux from a Stream source.
 * This class shows how to:
 * - Create a Flux from a List using stream()
 * - Subscribe multiple subscribers to the same Flux
 * - Handle stream consumption behavior
 */

public class FluxFromStream {
    /**
     * Main method demonstrating Flux creation from Stream
     * Note: Streams can only be consumed once, so each subscription
     * will create a new stream from the source list
     *
     * @param args Command line arguments (not used)
     */
    public static void main(String[] args) {
        // Create source list of integers
        var list = List.of(1,2,3,4,5);

        // Create Flux from stream using method reference
        var flux = Flux.fromStream(list::stream);

        // First subscriber will receive all items
        flux.subscribe(Util.subscriber("sub01"));

        // Second subscriber will receive all items from a new stream
        flux.subscribe(Util.subscriber("sub02"));

    }
}
