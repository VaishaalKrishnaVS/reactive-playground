package pg.reactive.reactiveprogramming.section07;

import lombok.extern.slf4j.Slf4j;
import pg.reactive.reactiveprogramming.common.Util;
import reactor.core.publisher.Flux;


@Slf4j
public class DefaultBehaviourDemo {
    public static void main(String[] args) {
        log.info("start");
        var flux = Flux.create(fluxSink -> {
                    for (int i = 0; i < 5; i++) fluxSink.next(i);
                    fluxSink.complete();
                }).doOnNext((n) -> log.info("value: {}", n));

        Runnable runnable = () -> flux.subscribe(Util.subscriber("sub01"));
        Thread.ofPlatform().start(runnable);
    }
}
