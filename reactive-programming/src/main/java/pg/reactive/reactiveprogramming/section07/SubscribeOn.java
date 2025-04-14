package pg.reactive.reactiveprogramming.section07;

import lombok.extern.slf4j.Slf4j;
import pg.reactive.reactiveprogramming.common.Util;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;


@Slf4j
public class SubscribeOn {
    public static void main(String[] args) {
        log.info("start");
        var flux = Flux.create(fluxSink -> {
                    for (int i = 0; i < 5; i++) fluxSink.next(i);
                    fluxSink.complete();
                })
                .doOnNext(v -> log.info("value: {}", v))
                .doFirst(() -> log.info("first1"))
                .subscribeOn(Schedulers.boundedElastic())
                .doFirst(() -> log.info("first2"));

        Runnable runnable01 = () -> flux.subscribe(Util.subscriber("sub01"));
        Runnable runnable02 = () -> flux.subscribe(Util.subscriber("sub02"));
        Thread.ofPlatform().start(runnable01);
        Thread.ofPlatform().start(runnable02);

        Util.sleep(Duration.ofSeconds(2));
    }
}
