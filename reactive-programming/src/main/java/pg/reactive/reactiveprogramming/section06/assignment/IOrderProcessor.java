package pg.reactive.reactiveprogramming.section06.assignment;

import reactor.core.publisher.Flux;

public interface IOrderProcessor {
    void consume(Order order);
    Flux<String> streamData();
}
