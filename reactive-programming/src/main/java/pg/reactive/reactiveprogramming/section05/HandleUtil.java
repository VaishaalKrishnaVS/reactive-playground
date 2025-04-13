package pg.reactive.reactiveprogramming.section05;

import pg.reactive.reactiveprogramming.common.Util;
import reactor.core.publisher.Flux;

public class HandleUtil {
    public static void main(String[] args) {
        Flux.<String>generate(synchronousSink -> synchronousSink.next(Util.faker().country().name()))
                .handle((name,sink)->{
                    sink.next(name);
                    if (name.equalsIgnoreCase("canada")) sink.complete();
                }).subscribe(Util.subscriber());
    }
}
