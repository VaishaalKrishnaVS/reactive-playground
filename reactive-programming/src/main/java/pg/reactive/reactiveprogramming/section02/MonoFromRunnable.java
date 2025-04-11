package pg.reactive.reactiveprogramming.section02;

import lombok.extern.slf4j.Slf4j;
import pg.reactive.reactiveprogramming.common.Util;
import reactor.core.publisher.Mono;


/**
 * Demonstrates usage of Mono.fromRunnable()
 *
 * fromRunnable() creates a Mono from a Runnable task that:
 * - Executes an action but does not return any value
 * - Completes empty after the action is executed
 * - Is useful for side effects like logging, notifications etc.
 * - Does not emit any data item
 * - Can be used when you want to perform an action and don't need a result
 */
@Slf4j
public class MonoFromRunnable {
    public static void main(String[] args) {
        getProduct(2)
                .subscribe(Util.subscriber());
        getProduct(3)
                .subscribe(Util.subscriber());
    }

    /**
     * Returns a Mono<String> based on product id
     * For id=2: Returns product name using fromSupplier
     * For other ids: Returns empty Mono after executing notification using fromRunnable
     */
    private static Mono<String> getProduct(long id){
        if(id==2) return Mono.fromSupplier(()-> Util.faker().commerce().productName());
        else return Mono.fromRunnable(()-> notifyBusiness(id));
    }

    /**
     * Side effect method that logs notification
     * Used as Runnable task in fromRunnable()
     */
    private static void notifyBusiness(long id){
        log.info("Sending notification for product {}", id);
    }
}
