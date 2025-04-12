package pg.reactive.reactiveprogramming.section03;

import pg.reactive.reactiveprogramming.common.Util;
import reactor.core.publisher.Flux;

/**
 * Demonstrates the usage of Flux.just() operator in Project Reactor
 * Flux.just() creates a Flux that emits the specified elements and then completes
 * - Supports different data types in the same Flux
 */
public class FluxJust {
    public static void main(String[] args) {
        // Creates a Flux that emits integers and strings sequentially
        Flux.just(1,2,3,4, "apple","banana","cat")
                .subscribe(Util.subscriber());
    }
}
