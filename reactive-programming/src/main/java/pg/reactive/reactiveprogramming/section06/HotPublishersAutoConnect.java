package pg.reactive.reactiveprogramming.section06;

import lombok.extern.slf4j.Slf4j;
import pg.reactive.reactiveprogramming.common.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;

/*
    almost same as publish().refCount(1).
    - does NOT stop when subscribers cancel. So it will start producing even for 0 subscribers once it started.
    - make it real hot - publish().autoConnect(0)
 */
@Slf4j
public class HotPublishersAutoConnect {
    public static void main(String[] args) {
        var movieFlux = movieStream().publish().autoConnect(0);
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
