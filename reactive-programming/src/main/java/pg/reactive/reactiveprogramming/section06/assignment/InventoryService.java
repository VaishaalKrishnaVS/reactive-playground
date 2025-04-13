package pg.reactive.reactiveprogramming.section06.assignment;

import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class InventoryService implements IOrderProcessor{
    private final Map<String, Integer> db = new HashMap<>();

    @Override
    public void consume(Order order) {
        var currentInventory = db.getOrDefault(order.category(), 500);
        var updatedInventory = currentInventory - order.quantity();
        db.put(order.category(), updatedInventory);
    }

    @Override
    public Flux<String> streamData() {
        return Flux.interval(Duration.ofSeconds(2))
                .map(i -> this.db.toString());
    }
}
