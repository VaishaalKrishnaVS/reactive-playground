package pg.reactive.reactiveprogramming.section05;

import pg.reactive.reactiveprogramming.common.Util;
import reactor.core.publisher.Flux;

public class SwitchEmpty {
    public static void main(String[] args) {
        Flux.range(1,10)
                .filter(integer -> integer>11)
                .switchIfEmpty(fallback())
                .subscribe(Util.subscriber());
    }
    private static Flux<Integer> fallback(){
        return Flux.range(100,10);
    }
}
