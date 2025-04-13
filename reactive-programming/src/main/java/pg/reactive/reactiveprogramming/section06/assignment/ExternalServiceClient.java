package pg.reactive.reactiveprogramming.section06.assignment;

import pg.reactive.reactiveprogramming.common.AbstractHttpClient;
import reactor.core.publisher.Flux;

import java.util.Objects;

public class ExternalServiceClient extends AbstractHttpClient {

    private Flux<Order> orderFlux;

    public Flux<Order> orderStream(){
        if(Objects.isNull(orderFlux)){
            this.orderFlux = getOrder();
        }
        return this.orderFlux;
    }

    public Flux<Order> getOrder(){
        return this.httpClient.get()
                .uri("/demo04/orders/stream")
                .responseContent()
                .asString()
                .map(this::parse)
                .publish()
                .refCount(2);
    }

    private Order parse(String message) {
        var arr = message.split(":");
        return new Order(
                arr[1],
                Integer.parseInt(arr[2]),
                Integer.parseInt(arr[3])
        );
    }
}
