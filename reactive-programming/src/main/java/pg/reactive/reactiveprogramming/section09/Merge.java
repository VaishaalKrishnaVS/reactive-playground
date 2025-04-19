package pg.reactive.reactiveprogramming.section09;

import lombok.extern.slf4j.Slf4j;
import pg.reactive.reactiveprogramming.common.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;

@Slf4j
public class Merge {
    public static void main(String[] args) {
//        demo01();
        demo02();

        Util.sleep(Duration.ofSeconds(15));
    }

    private static void demo01() {
        Flux.merge(producer01(), producer02(), producer03())
                .take(3)
                .subscribe(Util.subscriber());
    }

    private static void demo02() {
        producer01()
                .mergeWith(producer03())
                .mergeWith(producer02())
                .subscribe(Util.subscriber());
    }

    private static Flux<Integer> producer01() {
        return Flux.just(1,2,3)
                .doOnSubscribe(s->log.info("producing from 01"))
                .doOnNext(integer -> log.info("producer 01, value:: {}",integer))
                .delayElements(Duration.ofSeconds(1));
    }

    private static Flux<Integer> producer02() {
        return Flux.just(91,92,93)
                .doOnSubscribe(s->log.info("producing from 02"))
                .doOnNext(integer -> log.info("producer 02, value:: {}",integer))
                .delayElements(Duration.ofSeconds(2));
    }

    private static Flux<Integer> producer03() {
        return Flux.just(191,192,193)
                .doOnSubscribe(s->log.info("producing from 03"))
                .doOnNext(integer -> log.info("producer 03, value:: {}",integer))
                .delayElements(Duration.ofSeconds(2));
    }

}
