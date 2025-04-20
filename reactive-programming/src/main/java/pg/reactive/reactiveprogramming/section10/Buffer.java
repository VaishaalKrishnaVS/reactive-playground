package pg.reactive.reactiveprogramming.section10;

import pg.reactive.reactiveprogramming.common.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class Buffer {
    public static void main(String[] args) {
        demo04();
        Util.sleep(Duration.ofSeconds(30));
    }

    private static void demo01() {
        eventStream().buffer() // int-max value or the source has to complete
                .subscribe(Util.subscriber());
    }

    private static void demo02(){
        eventStream().buffer(3)
                .subscribe(Util.subscriber());
    }

    private static void demo04(){
        eventStream().bufferTimeout(3,Duration.ofMillis(1000)) // every 3 items or max 1 second
                .subscribe(Util.subscriber());
    }

    private static void demo03(){
        eventStream().buffer(Duration.ofMillis(500))
                .subscribe(Util.subscriber());
    }

    private static Flux<String> eventStream() {
        return Flux.interval(Duration.ofMillis(300))
                .take(10)
                .concatWith(Flux.never())
                .map(i -> "event_" + i);
    }
}
