package pg.reactive.reactiveprogramming.section11;

import lombok.extern.slf4j.Slf4j;
import pg.reactive.reactiveprogramming.common.Util;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Demonstrates retry functionality in Project Reactor
 * Retry allows resubscribing to a publisher when an error occurs
 */
@Slf4j
public class Retry {
    public static void main(String[] args) {
        demo02();
        Util.sleep(Duration.ofSeconds(4));
    }

    /**
     * Simple retry example that retries up to 9 times on any error
     * Will resubscribe immediately after each failure
     */
    public static void demo01() {
        getCountryName().retry(9).subscribe(Util.subscriber());
    }

    /**
     * Advanced retry example with:
     * - Fixed delay of 300ms between retries
     * - Maximum of 5 retry attempts
     * - Only retry on RuntimeException
     * - Custom error handling when retries are exhausted
     */
    public static void demo02() {
        getCountryName()
                .retryWhen(reactor.util.retry.Retry.fixedDelay(5, Duration.ofMillis(300))
                        .filter(throwable -> RuntimeException.class.equals(throwable.getClass()))
                        .onRetryExhaustedThrow((spec, signal) -> signal.failure()))
                .subscribe(Util.subscriber());
    }

    /**
     * Helper method that simulates a failing operation
     * - Uses AtomicInteger to track number of attempts
     * - Throws RuntimeException for first 4 attempts
     * - Returns random country name on 5th attempt
     * - Includes logging for errors and subscriptions
     */
    private static Mono<String> getCountryName() {
        var atomicInteger = new AtomicInteger(0);

        return Mono.fromSupplier(() -> {
                    if (atomicInteger.incrementAndGet() < 5) {
                        throw new RuntimeException("oops");
                    }
                    return Util.faker().country().name();
                })
                .doOnError(throwable -> log.info("ERROR: {}", throwable.getMessage()))
                .doOnSubscribe(subscription -> log.info("subscribing"));
    }
}
