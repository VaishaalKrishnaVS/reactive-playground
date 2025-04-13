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
public class HotPublishers {
    public static void main(String[] args) {
        var movieFlux = movieStream().share();
        Util.sleep(Duration.ofSeconds(2));
        movieFlux.subscribe(Util.subscriber("sub01"));
        Util.sleep(Duration.ofSeconds(3));
        movieFlux.subscribe(Util.subscriber("sub02"));
        Util.sleep(Duration.ofSeconds(15));

    }

    private static Flux<String> movieStream() {
        return Flux.generate(
                        () -> {
                            log.info("initiated");
                            return 1;
                        },
                        (state, sink) -> {
                            var scene = "movie scene" + state;
                            log.info("playing: {}", scene);
                            sink.next(scene);
                            return ++state;
                        }
                ).take(10)
                .delayElements(Duration.ofSeconds(1))
                .cast(String.class);
    }
}
