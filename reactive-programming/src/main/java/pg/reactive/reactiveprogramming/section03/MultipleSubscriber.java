package pg.reactive.reactiveprogramming.section03;

import lombok.extern.slf4j.Slf4j;
import pg.reactive.reactiveprogramming.common.Util;
import reactor.core.publisher.Flux;
/**
 * This class demonstrates the concept of multiple subscribers in Project Reactor.
 * It shows how a single Flux can have multiple subscribers performing different operations.
 */
@Slf4j
public class MultipleSubscriber {
    /**
     * Main method that demonstrates multiple subscribers on a single Flux
     *
     * @param args Command line arguments (not used)
     */
    public static void main(String[] args) {
        // Create a Flux with integers 1 through 9
        var flux = Flux.just(1,2,3,4,5,6,7,8,9);

        // First subscriber receives all numbers
        flux.subscribe(Util.subscriber("subscriber1"));

        // Second subscriber receives only even numbers using filter
        flux.filter(i->i%2==0)
                .subscribe(Util.subscriber("subscriber2"));

        // Third subscriber receives numbers with 'a' appended using map
        flux.map(integer -> integer+"a")
                .subscribe(Util.subscriber("subscriber3"));
    }
}