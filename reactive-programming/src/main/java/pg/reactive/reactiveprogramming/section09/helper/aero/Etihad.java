package pg.reactive.reactiveprogramming.section09.helper.aero;

import pg.reactive.reactiveprogramming.common.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class Etihad {
    public static final String name = "ETIHAD";

    public static Flux<Flight> getAeroplanes() {
        return Flux.range(1, Util.faker().random().nextInt(5, 10))
                .delayElements(Duration.ofMillis(Util.faker().random().nextInt(200, 500)))
                .map(integer -> new Flight(name, Util.faker().random().nextInt(400, 1000)))
                .transform(Util.fluxLogger(name));
    }
}
