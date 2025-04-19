package pg.reactive.reactiveprogramming.section09.helper;

import lombok.extern.slf4j.Slf4j;
import pg.reactive.reactiveprogramming.common.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class NameGenerator {
    private final List<String> redisMimicList = new ArrayList<>();
    public Flux<String> generateNames(){
        return Flux.generate(stringSynchronousSink -> {
            log.info("generating name");
            Util.sleep(Duration.ofSeconds(1));
            var name = Util.faker().harryPotter().character();
            redisMimicList.add(name);
            stringSynchronousSink.next(name);
        })
                .startWith(redisMimicList)
                .cast(String.class);
    }
}
