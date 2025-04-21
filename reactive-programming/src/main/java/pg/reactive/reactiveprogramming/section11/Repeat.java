package pg.reactive.reactiveprogramming.section11;

import pg.reactive.reactiveprogramming.common.Util;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Demonstrates different ways to use repeat() operator in Reactor
 * repeat() - Resubscribes to the source when complete, up to the specified number of times
 * repeat() without params - Repeats indefinitely until cancelled
 * repeat(long n) - Repeats n additional times (total times = n + 1)
 * repeat(BooleanSupplier supplier) - Repeats while supplier returns true
 * repeatWhen(Function) - Repeats when receiving signal from companion flux
 */
public class Repeat {
    public static void main(String[] args) {
        demo01();
        demo02();
        demo03();
        demo04();
        demo05();

        Util.sleep(Duration.ofSeconds(10));
    }

    private static void demo01() {
        getCountry().repeat(3)
                .subscribe(Util.subscriber());
    }

    private static void demo02() {
        getCountry().repeat()
                .takeUntil(s -> s.equalsIgnoreCase("canada"))
                .subscribe(Util.subscriber());
    }

    private static void demo03() {
        AtomicInteger counter = new AtomicInteger();
        getCountry().repeat(() -> counter.incrementAndGet() < 3)
                .subscribe(Util.subscriber());
    }

    private static void demo04() {
        getCountry()
                .repeatWhen(longFlux -> longFlux
                        .delayElements(Duration.ofSeconds(2))
                        .take(2))
                .subscribe(Util.subscriber());
    }

    private static void demo05(){
        Flux.just(1,2,3,4)
                .repeat(3)
                .repeat(2)
                .subscribe(Util.subscriber());
    }

    private static Mono<String> getCountry() {
        return Mono.fromSupplier(() -> Util.faker().country().name());
    }
}
