package pg.reactive.reactiveprogramming.section03;

import pg.reactive.reactiveprogramming.common.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class FluxInterval {
    public static void main(String[] args) {
        Flux.interval(Duration.ofMillis(500))
                .map(i-> Util.faker().harryPotter().character())
                .subscribe(Util.subscriber());

        Util.sleep(Duration.ofSeconds(3));
    }
}
