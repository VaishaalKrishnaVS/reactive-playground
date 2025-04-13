package pg.reactive.reactiveprogramming.section05;
/*
    Handle behaves like filter + map

    1 => -2
    4 => do not send
    7 => error
    everything else => send as it is
*/

import pg.reactive.reactiveprogramming.common.Util;
import reactor.core.publisher.Flux;

public class Handle {
    public static void main(String[] args) {
        Flux.range(1,10)
                .handle((val, sink)->{
                    switch (val){
                        case 2->sink.next(-2);
                        case 4->{}
                        case 7->sink.error(new RuntimeException("run time exception"));
                        default -> sink.next(val);
                    }
                })
                .subscribe(Util.subscriber());
    }
}
