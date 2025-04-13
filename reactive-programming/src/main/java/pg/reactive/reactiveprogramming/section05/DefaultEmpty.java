package pg.reactive.reactiveprogramming.section05;

import pg.reactive.reactiveprogramming.common.Util;
import reactor.core.publisher.Flux;

public class DefaultEmpty {
    public static void main(String[] args) {
        Flux.range(1,5)
                .filter(integer -> integer>5)
                .defaultIfEmpty(10)
                .subscribe(Util.subscriber());
    }
}
