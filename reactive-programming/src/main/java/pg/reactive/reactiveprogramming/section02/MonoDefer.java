package pg.reactive.reactiveprogramming.section02;

import lombok.extern.slf4j.Slf4j;
import pg.reactive.reactiveprogramming.common.Util;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.List;
@Slf4j
public class MonoDefer {
    // Mono.defer() creates a new publisher for each subscription
    // This ensures that the publisher is created lazily and fresh state for each subscriber
    public static void main(String[] args) {
        // Create deferred Mono that will invoke createPublisher() for each subscription
        Mono<Integer> deferredMono = Mono.defer(MonoDefer::createPublisher);

        // First subscription - will create new publisher
        deferredMono.subscribe(Util.subscriber("Sub 1"));

        // Second subscription - will create another new publisher
        deferredMono.subscribe(Util.subscriber("Sub 2"));

        // Third subscription with delay to demonstrate lazy creation
        Util.sleep(Duration.ofSeconds(5));
        deferredMono.subscribe(Util.subscriber("Sub 3"));
    }

    // Publisher factory method that will be called for each subscription
    private static Mono<Integer> createPublisher() {
        log.info("Creating publisher on demand"); // Log when publisher is created
        var list = List.of(1, 2, 3, 4, 5, 6);
        Util.sleep(Duration.ofSeconds(2)); // Simulate some work
        return Mono.fromSupplier(() -> sum(list)); // Create new Mono for each call
    }

    private static int sum(List<Integer> list) {
        log.info("Generating sum"); // Log when sum is generated
        return list.stream().mapToInt(Integer::intValue).sum(); // Calculate sum of list elements
    }
}


