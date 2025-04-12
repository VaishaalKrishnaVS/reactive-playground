package pg.reactive.reactiveprogramming.section03;

import pg.reactive.reactiveprogramming.common.Util;
import reactor.core.publisher.Flux;

import java.util.List;

public class FluxFromIterableOrArray {
    public static void main(String[] args) {
        var list = List.of(1,2,3,4,5);
        Flux.fromIterable(list).subscribe(Util.subscriber());

        Integer[] arr = {1,2,3,4};
        Flux.fromArray(arr).subscribe(Util.subscriber());
    }
}
