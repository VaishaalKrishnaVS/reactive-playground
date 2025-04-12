package pg.reactive.reactiveprogramming.section04;

import lombok.extern.slf4j.Slf4j;
import pg.reactive.reactiveprogramming.common.Util;
import reactor.core.publisher.Flux;
@Slf4j
public class FluxGenerateUntil {
    public static void main(String[] args) {
        log.info("running demo 01");
        demo1();
        log.info("running demo 02");
        demo2();
    }
    private static void demo1(){
        Flux.generate(synchronousSink -> {
            var name = Util.faker().country().name();
            synchronousSink.next(name);
            if(name.equalsIgnoreCase("canada")) synchronousSink.complete();
        }).subscribe(Util.subscriber());
    }
    private static void demo2(){
        Flux.<String>generate(synchronousSink -> {
            var name = Util.faker().country().name();
            synchronousSink.next(name);
        })
                .takeUntil(c->c.equalsIgnoreCase("canada"))
                .subscribe(Util.subscriber());
    }

}
