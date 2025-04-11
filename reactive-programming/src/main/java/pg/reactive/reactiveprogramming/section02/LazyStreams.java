package pg.reactive.reactiveprogramming.section02;

import lombok.extern.slf4j.Slf4j;

import java.util.stream.Stream;
/**
 * A class demonstrating lazy evaluation in Java Streams.
 * This example shows a simple stream operation with logging.
 */
@Slf4j
public class LazyStreams {
    /**
     * Main method that creates a stream with a single element and processes it
     * @param args Command line arguments (not used)
     */
    public static void main(String[] args) {
        Stream.of(1)
                .peek(integer -> log.info("value: {}",integer)) // Logs each element as it passes through
                .toList(); // Terminal operation that collects stream elements to a List
    }
}
