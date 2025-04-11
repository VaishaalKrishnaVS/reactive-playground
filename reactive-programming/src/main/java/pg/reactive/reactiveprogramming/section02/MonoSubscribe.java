/**
 * Demonstrates basic Mono subscription with Project Reactor.
 * Shows how to create a Mono and subscribe to it with full set of callbacks.
 */
package pg.reactive.reactiveprogramming.section02;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Slf4j
public class MonoSubscribe {
    /**
     * Main method demonstrating Mono subscription
     * @param args Command line arguments (not used)
     */
    public static void main(String[] args) {
        // Create a Mono that emits a single value
        var mono = Mono.just(1);

        // Subscribe to the Mono with four callbacks:
        // 1. onNext - Handles the emitted value
        // 2. onError - Handles any errors
        // 3. onComplete - Called when the sequence completes
        // 4. onSubscribe - Handles the subscription and requests data
        mono.subscribe(
                item -> log.info("Received: {}", item),
                err -> log.error("Error: {}", err.getMessage()),
                () -> log.info("Completed"),
                subscription -> subscription.request(1)
        );
    }
}
