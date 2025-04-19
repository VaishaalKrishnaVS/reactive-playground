package pg.reactive.reactiveprogramming.section09.helper.aero;

import pg.reactive.reactiveprogramming.common.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class AmericanAirlines {
    public static final String name = "AMERICAN AIRLINES";

    public static Flux<Flight> getAeroplanes() {
        return Flux.range(1, Util.faker().random().nextInt(5, 10))
                .delayElements(Duration.ofMillis(Util.faker().random().nextInt(300, 700)))
                .map(integer -> new Flight(name, Util.faker().random().nextInt(300, 1200)))
                .transform(Util.fluxLogger(name));
    }
}
