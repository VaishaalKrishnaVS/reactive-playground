package pg.reactive.reactiveprogramming.section08;

import lombok.extern.slf4j.Slf4j;
import pg.reactive.reactiveprogramming.common.Util;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;

@Slf4j
public class MultipleSubscribers {
    public static void main(String[] args) {
        var producer = Flux.generate(
                        () -> 1,
                        (state, sink) -> {
                            log.info("generating value: {}", state);
                            sink.next(state);
                            return ++state;
                        }
                )
                .cast(Integer.class)
                .subscribeOn(Schedulers.parallel());

        producer
                .limitRate(5)
                .publishOn(Schedulers.boundedElastic())
                .map(MultipleSubscribers::timeConsumingTask)
                .subscribe(Util.subscriber("sub01"));

        producer
                .take(100)
                .publishOn(Schedulers.boundedElastic())
                .subscribe(Util.subscriber("sub02"));

        Util.sleep(Duration.ofSeconds(30));

    }

    private static int timeConsumingTask(int i) {
        Util.sleep(Duration.ofSeconds(1));
        return i;
    }
}
