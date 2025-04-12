package pg.reactive.reactiveprogramming.section03.helper;

import pg.reactive.reactiveprogramming.common.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.List;
import java.util.stream.IntStream;

public class NameGenerator {
    public static List<String> getNamesList(int count){
        return IntStream.rangeClosed(1,count)
                .mapToObj(i->generateName())
                .toList();
    }
    public static Flux<String> getNamesFlux(int count){
        return Flux.range(1,count)
                .map(integer -> generateName());
    }
    private static String generateName(){
        Util.sleep(Duration.ofSeconds(1));
        return Util.faker().harryPotter().character();
    }
}
