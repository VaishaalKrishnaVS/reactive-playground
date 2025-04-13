package pg.reactive.reactiveprogramming.section05.assignment;

import reactor.core.publisher.Mono;

import java.time.Duration;

public class ProductServiceImpl implements IProductService{
    private final ExternalServiceClient externalServiceClient;

    public ProductServiceImpl(ExternalServiceClient externalServiceClient) {
        this.externalServiceClient = externalServiceClient;
    }

    @Override
    public Mono<String> getProductName(int id) {
        return externalServiceClient.getProductById(id)
                .timeout(Duration.ofSeconds(2), externalServiceClient.getTimeoutFallbackById(id))
                .switchIfEmpty(externalServiceClient.getEmptyFallbackById(id));
    }
}
