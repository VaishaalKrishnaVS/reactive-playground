package pg.reactive.reactiveprogramming.section10.assignment.groupby;

import pg.reactive.reactiveprogramming.common.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class Assignment {
    public static void main(String[] args) {
        getPurchaseOrder()
                .filter(OrderProcessingService.canProcess())
                .groupBy(PurchaseOrder::category)
                .flatMap(stringPurchaseOrderGroupedFlux ->
                        stringPurchaseOrderGroupedFlux.transform(OrderProcessingService.getProcessor(stringPurchaseOrderGroupedFlux.key())))
                .subscribe(Util.subscriber());

        Util.sleep(Duration.ofSeconds(30));
    }

    private static Flux<PurchaseOrder> getPurchaseOrder() {
        return Flux.interval(Duration.ofMillis(500))
                .map(i -> PurchaseOrder.create());
    }
}
