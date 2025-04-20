package pg.reactive.reactiveprogramming.section10.assignment.window;

import pg.reactive.reactiveprogramming.common.Util;
import reactor.core.publisher.Flux;

import java.nio.file.Path;
import java.time.Duration;
import java.util.concurrent.atomic.AtomicInteger;

public class Assignment {
    public static void main(String[] args) {

        var counter = new AtomicInteger(0);
        var fileNameFormat = "reactive-programming/src/main/resources/section10/file%d.txt";

        eventStream()
                .window(Duration.ofMillis(1800)) // just for demo
                .flatMap(flux -> FileWriter.create(flux, Path.of(fileNameFormat.formatted(counter.incrementAndGet()))))
                .subscribe();


        Util.sleep(Duration.ofSeconds(60));

    }

    private static Flux<String> eventStream() {
        return Flux.interval(Duration.ofMillis(500))
                .map(i -> "event-" + (i + 1));
    }
}
