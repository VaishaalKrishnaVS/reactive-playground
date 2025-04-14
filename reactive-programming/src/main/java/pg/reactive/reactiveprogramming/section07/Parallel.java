package pg.reactive.reactiveprogramming.section07;

import lombok.extern.slf4j.Slf4j;
import pg.reactive.reactiveprogramming.common.Util;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;

@Slf4j
public class Parallel {
    public static void main(String[] args) {
        Flux.range(1,10)
                .parallel(3)
                .runOn(Schedulers.parallel())
                .map(Parallel::process)
                //   .sequential()
                .map(i -> i + "a")
                .subscribe(Util.subscriber());

        Util.sleep(Duration.ofSeconds(30));
    }

    private static int process(int i){
        log.info("time consuming task {}", i);
        Util.sleep(Duration.ofSeconds(1));
        return i * 2;
    }

}
