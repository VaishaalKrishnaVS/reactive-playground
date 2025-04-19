package pg.reactive.reactiveprogramming.section09;

import lombok.extern.slf4j.Slf4j;
import pg.reactive.reactiveprogramming.common.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;

@Slf4j
public class ConcatWith {
    public static void main(String[] args) {
        demo01();
        demo02();
        demo03();

        Util.sleep(Duration.ofSeconds(9));
    }

    private static void demo01() {
        producer01()
                .concatWithValues(-1,0)
                .subscribe(Util.subscriber());
    }

    private static void demo02() {
        producer01()
                .concatWith(producer02())
                .subscribe(Util.subscriber());
    }

    private static void demo03(){
        Flux.concat(producer01(), producer02())
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
}
