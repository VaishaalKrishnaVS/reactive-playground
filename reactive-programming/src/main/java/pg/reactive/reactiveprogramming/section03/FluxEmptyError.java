package pg.reactive.reactiveprogramming.section03;

import pg.reactive.reactiveprogramming.common.Util;
import reactor.core.publisher.Flux;

public class FluxEmptyError {
    public static void main(String[] args) {
        Flux.empty()
                .subscribe(Util.subscriber());
        Flux.error(new RuntimeException("some random exception"))
                .subscribe(Util.subscriber());
    }
}
