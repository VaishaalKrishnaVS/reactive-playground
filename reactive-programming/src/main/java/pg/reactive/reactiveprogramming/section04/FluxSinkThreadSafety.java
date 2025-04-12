package pg.reactive.reactiveprogramming.section04;

import lombok.extern.slf4j.Slf4j;
import pg.reactive.reactiveprogramming.common.Util;
import pg.reactive.reactiveprogramming.section04.helper.NameGenerator;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.ArrayList;
/**
 * This code demonstrates thread safety issues with ArrayList
 * Array List is not thread-safe and multiple threads accessing it concurrently can lead to data corruption
 * The size of the list at the end may be less than expected (10000) due to race conditions
 */

@Slf4j
public class FluxSinkThreadSafety {
    public static void main(String[] args) {
        arrayListDemo();
        fluxDemo();
    }
    private static void arrayListDemo(){
        var list = new ArrayList<Integer>();
        Runnable runnable = () -> {
            for(int i=0;i<1000;i++) list.add(i);
        };
        for(int i=0;i<10;i++){
            Thread.ofPlatform().start(runnable);
        }
        Util.sleep(Duration.ofSeconds(4));
        log.info("List size: {}", list.size());
    }
    private static void fluxDemo(){
        var list = new ArrayList<String>();
        var generator = new NameGenerator();
        var flux = Flux.create(generator);
        flux.subscribe(list::add);
        Runnable runnable = () -> {
            for (int i = 0; i < 1000; i++) {
                generator.generate();
            }
        };
        for (int i = 0; i < 10; i++) {
            Thread.ofPlatform().start(runnable);
        }
        Util.sleep(Duration.ofSeconds(4));
        log.info("list size: {}", list.size());
    }

}
