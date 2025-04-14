package pg.reactive.reactiveprogramming.section08;

import lombok.extern.slf4j.Slf4j;
import pg.reactive.reactiveprogramming.common.Util;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;

@Slf4j
public class BackPressureDefaultHandling {
    public static void main(String[] args) {
        System.setProperty("reactor.bufferSize.small", "16");
        var producer = Flux.generate(
                () ->  1,
                (state, sink) -> {
                    log.info("generating value: {}", state);
                    sink.next(state);
                    return ++state;
                }
        )
                .cast(Integer.class)
                .subscribeOn(Schedulers.parallel());

        producer.publishOn(Schedulers.boundedElastic())
                .map(BackPressureDefaultHandling::timeConsumingTask)
                .subscribe(Util.subscriber());

        Util.sleep(Duration.ofSeconds(30));

    }
    private static int timeConsumingTask(int i){
        Util.sleep(Duration.ofSeconds(1));
        return i;
    }
}
