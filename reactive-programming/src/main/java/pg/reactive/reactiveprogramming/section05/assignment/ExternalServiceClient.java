package pg.reactive.reactiveprogramming.section05.assignment;

import pg.reactive.reactiveprogramming.common.AbstractHttpClient;
import reactor.core.publisher.Mono;

public class ExternalServiceClient extends AbstractHttpClient {

    public Mono<String> getProductById(int id){
        return this.httpClient.get()
                .uri("/demo03/product/"+id)
                .responseContent()
                .asString()
                .next();
    }

    public Mono<String> getEmptyFallbackById(int id){
        return this.httpClient.get()
                .uri("/demo03/empty-fallback/product/"+id)
                .responseContent()
                .asString()
                .next();
    }

    public Mono<String> getTimeoutFallbackById(int id){
        return this.httpClient.get()
                .uri("/demo03/timeout-fallback/product/"+id)
                .responseContent()
                .asString()
                .next();
    }

}
