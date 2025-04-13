package pg.reactive.reactiveprogramming.section05;

import lombok.extern.slf4j.Slf4j;
import pg.reactive.reactiveprogramming.common.Util;
import reactor.core.publisher.Flux;

import java.util.function.UnaryOperator;

@Slf4j
public class Transform {
    record Customer(int id, String name) {
    }

    record PurchaseOrder(String name, int quantity, int amount) {
    }

    public static void main(String[] args) {
        getCustomer()
                .transform(addDebugger())
                .subscribe(Util.subscriber());
        getPurchase()
                .transform(addDebugger())
                .subscribe(Util.subscriber());
    }

    private static Flux<Customer> getCustomer() {
        return Flux.range(1, 5)
                .map(integer -> new Customer(integer, Util.faker().name().firstName()));
    }

    private static Flux<PurchaseOrder> getPurchase() {
        return Flux.range(1, 5)
                .map(integer -> new PurchaseOrder(Util.faker().commerce().productName(), integer, integer * 10));
    }

    private static <T> UnaryOperator<Flux<T>> addDebugger() {
        return tFlux -> tFlux
                .doOnNext(i -> log.info("received: {}", i))
                .doOnComplete(() -> log.info("completed"))
                .doOnError(err -> log.error("error", err));
    }

}
