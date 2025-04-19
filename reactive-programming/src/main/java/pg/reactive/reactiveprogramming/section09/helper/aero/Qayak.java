package pg.reactive.reactiveprogramming.section09.helper.aero;

import reactor.core.publisher.Flux;

import java.time.Duration;

public class Qayak {
    public static Flux<Flight> getFlights() {
        return Flux.merge(
                Emirates.getAeroplanes(),
                AmericanAirlines.getAeroplanes(),
                Etihad.getAeroplanes()
        ).take(Duration.ofSeconds(5));
    }
}
