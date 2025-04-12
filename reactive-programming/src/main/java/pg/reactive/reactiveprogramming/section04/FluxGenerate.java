package pg.reactive.reactiveprogramming.section04;

import lombok.extern.slf4j.Slf4j;
import pg.reactive.reactiveprogramming.common.Util;
import reactor.core.publisher.Flux;
/*
    Flux generate
    - invokes the given lambda expression again and again based on downstream demand.
    - We can emit only one value at a time
    - will stop when complete method is invoked
    - will stop when error method is invoked
    - will stop downstream cancels
 */
@Slf4j
public class FluxGenerate {
    public static void main(String[] args) {
        Flux.generate(synchronousSink -> {
                    log.info("invoked");
                    synchronousSink.next(1);
                })
                .take(4)
                .subscribe(Util.subscriber());
    }
}
