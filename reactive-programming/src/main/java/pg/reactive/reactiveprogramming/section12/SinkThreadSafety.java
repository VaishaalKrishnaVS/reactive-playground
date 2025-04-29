package pg.reactive.reactiveprogramming.section12;

import reactor.core.publisher.Sinks;

public class SinkThreadSafety {
    public static void main(String[] args) {

    }
    private static void demo01(){
        var sink = Sinks.many().unicast();
    }
}
