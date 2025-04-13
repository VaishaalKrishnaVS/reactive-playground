package pg.reactive.reactiveprogramming.section05;

import lombok.extern.slf4j.Slf4j;
import pg.reactive.reactiveprogramming.common.Util;
import reactor.core.publisher.Mono;

import java.time.Duration;

@Slf4j
public class Timeout {
    public static void main(String[] args) {

        getProductName()
                .timeout(Duration.ofSeconds(1), fallback())
                .subscribe(Util.subscriber());

        Util.sleep(Duration.ofSeconds(5));

    }

    private static Mono<String> getProductName() {
        return Mono.fromSupplier(() -> "service-" + Util.faker().commerce().productName())
                .delayElement(Duration.ofMillis(1900));
    }

    private static Mono<String> fallback() {
        return Mono.fromSupplier(() -> "fallback-" + Util.faker().commerce().productName())
                .delayElement(Duration.ofMillis(300))
                .doFirst(() -> log.info("do first"));
    }
}
