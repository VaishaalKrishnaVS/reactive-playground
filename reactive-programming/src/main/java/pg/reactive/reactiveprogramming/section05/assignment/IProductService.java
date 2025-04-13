package pg.reactive.reactiveprogramming.section05.assignment;

import reactor.core.publisher.Mono;

public interface IProductService {
    Mono<String> getProductName(int id);
}
