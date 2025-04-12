package pg.reactive.reactiveprogramming.section04.helper;


import pg.reactive.reactiveprogramming.common.Util;
import reactor.core.publisher.FluxSink;

import java.util.function.Consumer;

public class NameGenerator implements Consumer<FluxSink<String>> {
    private FluxSink<String> sink;
    @Override
    public void accept(FluxSink<String> stringFluxSink) {
        this.sink=stringFluxSink;
    }
    public void generate(){
        this.sink.next(Util.faker().harryPotter().character());
    }
}
