package pg.reactive.reactiveprogramming.section03.assignment;

import pg.reactive.reactiveprogramming.common.AbstractHttpClient;
import reactor.core.publisher.Flux;

public class Client extends AbstractHttpClient {

    public Flux<Integer> getPrices() {
        return this.httpClient
                .get()
                .uri("/demo02/stock/stream")
                .responseContent()
                .asString()
                .map(Integer::parseInt);
    }
}
