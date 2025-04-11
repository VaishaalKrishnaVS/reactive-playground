package pg.reactive.reactiveprogramming.section02;

import lombok.extern.slf4j.Slf4j;
import pg.reactive.reactiveprogramming.common.Util;
import reactor.core.publisher.Mono;

import java.util.List;


/**
 * Demonstrates the usage of Mono.fromSupplier() to create reactive streams
 * Shows the difference between lazy and eager evaluation
 */
@Slf4j
public class MonoFromSupplier {
    /**
     * Main method that demonstrates Mono.fromSupplier behavior
     * @param args command line arguments (not used)
     */
    public static void main(String[] args) {
        // Create a sample list of integers
        var list = List.of(1,2,3);

        // This will not trigger the supplier since there is no subscription
        log.info("this is lazy and will not produce");
        Mono.fromSupplier(()->sum(list));

        // This will trigger the supplier since we subscribe to it
        log.info("this is eager and will produce");
        Mono.fromSupplier(()->sum(list)).subscribe(Util.subscriber());
    }

    /**
     * Calculates sum of integers in a list
     * @param list List of integers to sum
     * @return sum of all integers in the list
     */
    public static int sum(List<Integer> list) {
        log.info("Generating sum");
        return list.stream().mapToInt(Integer::intValue).sum();

    }
}
