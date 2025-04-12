package pg.reactive.reactiveprogramming.section03;

import pg.reactive.reactiveprogramming.common.Util;
import reactor.core.publisher.Flux;
/**
 * Demonstrates the usage of Flux.range() and various transformations in Reactor
 * This class shows different ways to work with Flux streams using range, map and log operators
 */
public class FluxRange {
    public static void main(String[] args) {
        // Example 1: Basic range from 1 to 10
        // Creates a Flux that emits integers from 1 to 10 sequentially
        Flux.range(1,10)
                .subscribe(Util.subscriber());

        // Example 2: Range with name mapping
        // Creates a Flux of 10 random first names by mapping each number to a generated name
        Flux.range(1,10)
                .map(i-> Util.faker().name().firstName())
                .subscribe(Util.subscriber());

        // Example 3: Range with logging and Harry Potter character mapping
        // Creates a Flux of 5 Harry Potter characters with logging before and after the map operation
        // The log() operator helps debug the flow of data through the stream
        Flux.range(1,5)
                .log()
                .map(integer -> Util.faker().harryPotter().character())
                .log()
                .subscribe(Util.subscriber());
    }
}
