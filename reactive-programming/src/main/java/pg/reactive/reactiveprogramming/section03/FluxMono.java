package pg.reactive.reactiveprogramming.section03;

import pg.reactive.reactiveprogramming.common.Util;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class FluxMono {
    public static void main(String[] args) {
        fluxToMono();
        monoToFlux();
    }

    public static void fluxToMono() {
        var flux = Flux.just(1,2,3);
        flux.next().subscribe(Util.subscriber());
        Mono.from(flux).subscribe(Util.subscriber());
    }
    public static void monoToFlux(){
        var mono = Mono.just("data");
        Flux.from(mono).subscribe(Util.subscriber());

    }
}
