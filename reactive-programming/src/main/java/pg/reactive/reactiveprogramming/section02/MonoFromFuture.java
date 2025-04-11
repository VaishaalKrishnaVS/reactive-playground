package pg.reactive.reactiveprogramming.section02;

import lombok.extern.slf4j.Slf4j;
import pg.reactive.reactiveprogramming.common.Util;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.concurrent.CompletableFuture;


@Slf4j
public class MonoFromFuture {

    /**
     * Demonstrates conversion of CompletableFuture to Mono using fromFuture()
     *
     * fromFuture() creates a Mono from a CompletableFuture
     * - The Mono completes with the value from the Future when it completes
     * - If the Future completes exceptionally, the Mono will error
     * - The Future execution starts eagerly (when created), not lazily
     * - Need to ensure application runs long enough for Future to complete
     */
    public static void main(String[] args) {
        Mono.fromFuture(MonoFromFuture::getName)
                .subscribe(Util.subscriber());
        Util.sleep(Duration.ofSeconds(3));
    }

    /**
     * Creates a CompletableFuture that asynchronously generates a random name
     * @return CompletableFuture<String> containing a random full name
     */
    private static CompletableFuture<String> getName(){
        return CompletableFuture.supplyAsync(()-> {
            log.info("Generating name");
            return Util.faker().name().fullName();
        });
    }
}

