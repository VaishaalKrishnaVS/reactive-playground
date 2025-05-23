package pg.reactive.reactiveprogramming.section04;

import pg.reactive.reactiveprogramming.common.Util;
import reactor.core.publisher.Flux;

public class FluxWithState {
    public static void main(String[] args) {
        Flux.generate(
                ()->0,
                (counter,sink)->{
                    var country = Util.faker().country().name();
                    sink.next(counter+": "+country);
                    counter++;
                    if(counter==10||country.equalsIgnoreCase("canada")) sink.complete();
                    return counter;
                }
        ).subscribe(Util.subscriber());
    }
}
