package pg.reactive.reactiveprogramming.section06.assignment;

import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class RevenueService implements IOrderProcessor{
    private final Map<String, Integer> db = new HashMap<>();

    @Override
    public void consume(Order order) {
        var currentRevenue = db.getOrDefault(order.category(), 0);
        var updatedRevenue = currentRevenue + order.price();
        db.put(order.category(), updatedRevenue);
    }

    @Override
    public Flux<String> streamData() {
        return Flux.interval(Duration.ofSeconds(2))
                .map(i -> this.db.toString());
    }
}
