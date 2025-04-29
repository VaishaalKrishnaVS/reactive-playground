package pg.reactive.reactiveprogramming.section12;

import lombok.extern.slf4j.Slf4j;
import pg.reactive.reactiveprogramming.common.Util;
import reactor.core.publisher.Sinks;
@Slf4j
public class SinkOne {
    public static void main(String[] args) {
        demo01();
        demo02();
        demo3();
    }
    // exploring sink methods to emit item / empty / error
    private static void demo01() {
        Sinks.One<Object> sink = Sinks.one();
        var mono = sink.asMono();

        mono.subscribe(Util.subscriber());

//        sink.tryEmitValue("something");
//        sink.tryEmitEmpty();
        sink.tryEmitError(new RuntimeException("oops"));
    }

    // we can have multiple subscribers
    private static void demo02(){
        var sink = Sinks.one();
        var mono = sink.asMono();

        mono.subscribe(Util.subscriber("Sub01"));
        mono.subscribe(Util.subscriber("Sub02"));

        sink.tryEmitValue("hii");
    }

    // emit failure handler - we can not emit after complete
    private static void demo3(){
        var sink = Sinks.one();
        var mono = sink.asMono();

        mono.subscribe(Util.subscriber("sam"));

        sink.emitValue("hi", ((signalType, emitResult) -> {
            log.info("hi");
            log.info(signalType.name());
            log.info(emitResult.name());
            return false;
        }));

        sink.emitValue("hello", ((signalType, emitResult) -> {
            log.info("hello");
            log.info(signalType.name());
            log.info(emitResult.name());
            return false;
        }));

    }
}
