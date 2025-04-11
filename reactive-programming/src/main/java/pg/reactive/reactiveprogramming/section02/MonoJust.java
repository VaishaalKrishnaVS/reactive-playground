package pg.reactive.reactiveprogramming.section02;

import pg.reactive.reactiveprogramming.section01.subscriber.SubscriberImpl;
import reactor.core.publisher.Mono;

/**
 * Demonstrates basic usage of Mono.just() in Project Reactor
 * A Mono represents a stream that emits at most one item
 */
public class MonoJust {
    public static void main(String[] args) {
        // Creates a Mono that emits a single String value "ball"
        var mono = Mono.just("ball");

        // Creates a custom subscriber to receive the emitted value
        var subscriber = new SubscriberImpl();

        // Subscribes to the Mono to start receiving values
        mono.subscribe(subscriber);

        // Requests 10 items from the subscription (note: Mono will only emit 1 item regardless)
        subscriber.getSubscription().request(10);

        // Additional request for 10 more items (will have no effect as Mono already completed)
        subscriber.getSubscription().request(10);

        // Cancels the subscription (not necessary here as Mono already completed)
        subscriber.getSubscription().cancel();
    }
}
