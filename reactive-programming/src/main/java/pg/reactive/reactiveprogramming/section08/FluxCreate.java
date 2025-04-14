package pg.reactive.reactiveprogramming.section08;

import lombok.extern.slf4j.Slf4j;
import pg.reactive.reactiveprogramming.common.Util;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;

@Slf4j
public class FluxCreate {
    public static void main(String[] args) {
        System.setProperty("reactor.bufferSize.small", "16");
        var producer = Flux.create(
                        (sink) -> {
                            for (int i = 1; i <= 500 && !sink.isCancelled(); i++) {
                                log.info("generating {}", i);
                                sink.next(i);
                                Util.sleep(Duration.ofMillis(50));
                            }
                            sink.complete();
                        }
                )
                .cast(Integer.class)
                .subscribeOn(Schedulers.parallel());

        producer
                .limitRate(1)
                .publishOn(Schedulers.boundedElastic())
                .map(FluxCreate::timeConsumingTask)
                .subscribe(Util.subscriber());

        Util.sleep(Duration.ofSeconds(30));

    }

    private static int timeConsumingTask(int i) {
        Util.sleep(Duration.ofSeconds(1));
        return i;
    }
}
