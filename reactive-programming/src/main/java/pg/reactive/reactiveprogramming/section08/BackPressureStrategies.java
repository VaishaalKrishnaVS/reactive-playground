package pg.reactive.reactiveprogramming.section08;

import lombok.extern.slf4j.Slf4j;
import pg.reactive.reactiveprogramming.common.Util;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;

@Slf4j
public class BackPressureStrategies {
    public static void main(String[] args) {
        log.info("start");
        var producer = Flux.create(
                        (sink) -> {
                            for (int i = 1; i <= 600 && !sink.isCancelled(); i++) {
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
//                .onBackpressureBuffer()
//                 .onBackpressureError()
//                 .onBackpressureBuffer(10)
//                 .onBackpressureDrop()
                .onBackpressureLatest()
                .publishOn(Schedulers.boundedElastic())
                .map(BackPressureStrategies::timeConsumingTask)
                .subscribe(Util.subscriber());

        Util.sleep(Duration.ofSeconds(30));

    }

    private static int timeConsumingTask(int i) {
        Util.sleep(Duration.ofSeconds(1));
        return i;
    }
}
