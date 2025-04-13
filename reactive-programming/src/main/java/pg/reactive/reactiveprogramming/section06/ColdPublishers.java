package pg.reactive.reactiveprogramming.section06;

import lombok.extern.slf4j.Slf4j;
import pg.reactive.reactiveprogramming.common.Util;
import reactor.core.publisher.Flux;

import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
public class ColdPublishers {
    public static void main(String[] args) {
        AtomicInteger j = new AtomicInteger();
        var flux = Flux.create(fluxSink -> {
            log.info("invoked");
            for(int i=0;i<5;i++){
                fluxSink.next(j.incrementAndGet());
            }
            fluxSink.complete();
        });

        flux.subscribe(Util.subscriber("sub01"));
        flux.subscribe(Util.subscriber("sub02"));
    }
}
