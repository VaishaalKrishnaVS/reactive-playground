package pg.reactive.reactiveprogramming.section02.client;

import pg.reactive.reactiveprogramming.common.AbstractHttpClient;
import reactor.core.publisher.Mono;

public class ExternalServiceClient extends AbstractHttpClient {

    public Mono<String> getProductName(int id){
        return this.httpClient.get()
                .uri("/demo01/product/"+id)
                .responseContent()
                .asString()
                .next();
    }
}
