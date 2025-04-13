package pg.reactive.reactiveprogramming.section05;

import pg.reactive.reactiveprogramming.common.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class Delay {
    public static void main(String[] args) {
        Flux.range(1,10)
                .delayElements(Duration.ofSeconds(1))
                .subscribe(Util.subscriber());
        Util.sleep(Duration.ofSeconds(12));
    }
}
