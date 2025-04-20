package pg.reactive.reactiveprogramming.section10;

import lombok.extern.slf4j.Slf4j;
import pg.reactive.reactiveprogramming.common.Util;
import reactor.core.publisher.Flux;
import reactor.core.publisher.GroupedFlux;
import reactor.core.publisher.Mono;

import java.time.Duration;

@Slf4j
public class GroupBy {
    public static void main(String[] args) {
        Flux.range(1, 30)
                .delayElements(Duration.ofSeconds(1))
                .groupBy(integer -> integer % 2)
                .flatMap(GroupBy::process)
                .subscribe(Util.subscriber());

        Util.sleep(Duration.ofSeconds(60));
    }

    private static Mono<Void> process(GroupedFlux<Integer, Integer> groupedFlux) {
        log.info("received flux for {}", groupedFlux.key());
        return groupedFlux.doOnNext(i -> log.info("key: {}, item: {}", groupedFlux.key(), i))
                .then();
    }
}
