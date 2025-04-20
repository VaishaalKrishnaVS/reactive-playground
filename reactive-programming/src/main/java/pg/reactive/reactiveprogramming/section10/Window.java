package pg.reactive.reactiveprogramming.section10;

import pg.reactive.reactiveprogramming.common.Util;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

public class Window {
    public static void main(String[] args) {
        eventStream().window(Duration.ofMillis(1500))
                .flatMap(Window::processEvents)
                .subscribe(Util.subscriber());
        Util.sleep(Duration.ofSeconds(10));
    }

    private static Flux<String> eventStream() {
        return Flux.interval(Duration.ofMillis(500))
                .map(i -> "event_" + (i + 1));
    }

    private static Mono<Void> processEvents(Flux<String> flux) {
        return flux.doOnNext(e -> System.out.print("*"))
                .doOnComplete(System.out::println)
                .then();
    }
}
