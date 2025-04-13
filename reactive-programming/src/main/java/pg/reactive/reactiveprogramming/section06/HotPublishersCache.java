package pg.reactive.reactiveprogramming.section06;

import lombok.extern.slf4j.Slf4j;
import pg.reactive.reactiveprogramming.common.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;

/*
    Hot - 1 data producer for all the subscribers.
    share => publish().refCount(1)
    It needs 1 min subscriber to emit data.
    It stops when there is 0 subscriber.
    Re-subscription - It starts again where there is a new subscriber.
    To have min 2 subscribers, use publish().refCount(2);
 */
@Slf4j
public class HotPublishersCache {
    public static void main(String[] args) {
        var stockFlux = stockStream().replay(1).autoConnect(0);
        Util.sleep(Duration.ofSeconds(4));
        stockFlux.subscribe(Util.subscriber("sub01"));
        Util.sleep(Duration.ofSeconds(4));
        stockFlux.subscribe(Util.subscriber("sub02"));
        Util.sleep(Duration.ofSeconds(15));

    }

    private static Flux<Integer> stockStream() {
        return Flux.generate(integerSynchronousSink ->
                integerSynchronousSink.next(Util.faker().random().nextInt(10,100)))
                .delayElements(Duration.ofSeconds(3))
                .doOnNext(price -> log.info("emitting price: {}", price))
                .cast(Integer.class);
    }
}
