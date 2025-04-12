package pg.reactive.reactiveprogramming.section04;

import lombok.extern.slf4j.Slf4j;
import pg.reactive.reactiveprogramming.common.Util;
import reactor.core.publisher.Flux;
@Slf4j
public class TakeOperator {
    public static void main(String[] args) {
        log.info("Demo for take::");
        takeDemo();
        log.info("Demo for takeWhile");
        takeWhileDemo();
        log.info("Demo for takeUntil");
        takeUntilDemo();
    }
    private static void takeDemo(){
        Flux.range(1,10)
                .take(3)
                .subscribe(Util.subscriber());
    }
    private static void takeWhileDemo(){
        Flux.range(1,10)
                .takeWhile(integer -> integer<5)
                .subscribe(Util.subscriber());
    }
    private static void takeUntilDemo(){
        Flux.range(1,10)
                .takeUntil(integer -> integer<3)
                .subscribe(Util.subscriber());
    }
}
