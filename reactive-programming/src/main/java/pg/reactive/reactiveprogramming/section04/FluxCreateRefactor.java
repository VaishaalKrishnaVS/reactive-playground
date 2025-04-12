package pg.reactive.reactiveprogramming.section04;

import pg.reactive.reactiveprogramming.common.Util;
import pg.reactive.reactiveprogramming.section04.helper.NameGenerator;
import reactor.core.publisher.Flux;


public class FluxCreateRefactor {
    public static void main(String[] args) {
        var generator  = new NameGenerator();
        var flux = Flux.create(generator);
        flux.subscribe(Util.subscriber());

        for(int i =0;i<5;i++){
            generator.generate();
        }
    }
}
