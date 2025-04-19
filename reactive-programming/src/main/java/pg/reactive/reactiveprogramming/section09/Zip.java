package pg.reactive.reactiveprogramming.section09;

import pg.reactive.reactiveprogramming.common.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class Zip {
    record Car(String body, String engine, String tyre){}

    public static void main(String[] args) {
        Flux.zip(getBody(), getEngine(), getTyre())
                .map(t -> new Car(t.getT1(), t.getT3(), t.getT2()))
                .subscribe(Util.subscriber());

        Util.sleep(Duration.ofSeconds(3));
    }

    private static Flux<String> getBody() {
        return Flux.range(1, 3)
                .map(integer -> "body_" + integer)
                .delayElements(Duration.ofMillis(200));
    }

    private static Flux<String> getTyre() {
        return Flux.range(1, 10)
                .map(integer -> "tyre" + integer)
                .delayElements(Duration.ofMillis(100));
    }

    private static Flux<String> getEngine() {
        return Flux.range(1, 2)
                .map(integer -> "engine_" + integer)
                .delayElements(Duration.ofMillis(400));
    }
}
