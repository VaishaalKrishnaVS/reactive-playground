package pg.reactive.reactiveprogramming.section02;

import lombok.extern.slf4j.Slf4j;
import pg.reactive.reactiveprogramming.common.Util;
import reactor.core.publisher.Mono;

import java.util.List;

@Slf4j
public class MonoFromCallable {

    /**
     * Mono.fromCallable() is used when we want to execute a blocking operation in a non-blocking way
     * It is useful for:
     * - Wrapping synchronous/blocking operations that return a value
     * - Deferring execution until subscription time
     * - Handling operations that may throw exceptions
     * - Converting callback-based APIs to reactive streams
     *
     * The callable is not executed until someone subscribes to the Mono
     * Any exceptions thrown by the callable will be propagated through the Mono's error channel
     */
    public static void main(String[] args) {

        var list = List.of(1, 2, 3);
        Mono.fromCallable(() -> sum(list))
                .subscribe(Util.subscriber());

    }

    /**
     * Example blocking operation that:
     * - Takes a list of integers as input
     * - Logs the operation
     * - Calculates and returns the sum
     * - May throw exceptions which will be handled by the Mono
     */
    private static int sum(List<Integer> list) throws Exception {
        log.info("finding the sum of {}", list);
        return list.stream().mapToInt(a -> a).sum();
    }
}

