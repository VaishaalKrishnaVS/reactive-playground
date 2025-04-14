package pg.reactive.reactiveprogramming.section08;

import lombok.extern.slf4j.Slf4j;
import pg.reactive.reactiveprogramming.common.Util;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;


/**
 * LimitRate demonstration class
 *
 * limitRate() is used to implement backpressure by limiting how many items the publisher
 * emits before waiting for acknowledgment from the subscriber.
 *
 * By default,:
 * - When limitRate(n) is used, the subscriber requests 75% of n items initially
 * - After processing 75% of n items, it requests the remaining 25%
 * - This cycle continues, maintaining a buffer of 25% while processing
 *
 * For example with limitRate(100):
 * - Initial request: 75 items
 * - After processing 75 items: requests next 25
 * - Maintains this 75/25 pattern
 *
 * You can override this behavior by using limitRate(n, lowTide):
 * - n: max number of items requested
 * - lowTide: threshold at which to request more items
 */
@Slf4j
public class LimitRate {
    public static void main(String[] args) {
        var producer = Flux.generate(
                        () -> 1,
                        (state, sink) -> {
                            log.info("generating value: {}", state);
                            sink.next(state);
                            return ++state;
                        }
                )
                .cast(Integer.class)
                .subscribeOn(Schedulers.parallel());

        // In this example, limitRate(5) means:
        // - Initial request: 4 items (75% of 5)
        // - After processing 4 items, requests 1 more
        // - This cycle repeats, maintaining backpressure
        producer
                .limitRate(5)
                .publishOn(Schedulers.boundedElastic())
                .map(LimitRate::timeConsumingTask)
                .subscribe(Util.subscriber());

        Util.sleep(Duration.ofSeconds(30));

    }

    private static int timeConsumingTask(int i) {
        Util.sleep(Duration.ofSeconds(1));
        return i;
    }
}
