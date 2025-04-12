package pg.reactive.reactiveprogramming.section04;

import lombok.extern.slf4j.Slf4j;
import pg.reactive.reactiveprogramming.common.Util;
import pg.reactive.reactiveprogramming.section01.subscriber.SubscriberImpl;
import reactor.core.publisher.Flux;

import java.time.Duration;

@Slf4j
public class FluxCreateDownstream {
    public static void main(String[] args) {
        produceEarly();
        produceOnDemand();
    }
    private static void produceEarly(){
        var subscriber  =  new SubscriberImpl();
        Flux.<String>create(fluxSink -> {
            for(int i=0;i<10;i++){
                var name = Util.faker().harryPotter().character();
                log.info("generated name: {}", name);
                fluxSink.next(name);
            }
        }).subscribe(subscriber);

        Util.sleep(Duration.ofSeconds(2));
        subscriber.getSubscription().request(2);
        Util.sleep(Duration.ofSeconds(2));
        subscriber.getSubscription().request(2);
        Util.sleep(Duration.ofSeconds(2));
        subscriber.getSubscription().cancel();
        subscriber.getSubscription().request(2);
    }
    private static void produceOnDemand(){
        var subscriber  =  new SubscriberImpl();
        Flux.<String>create(fluxSink -> {
            fluxSink.onRequest(r->{
                for(int i=0;i<r && !fluxSink.isCancelled();i++){
                    var name = Util.faker().harryPotter().character();
                    log.info("generated name:: {}", name);
                    fluxSink.next(name);
                }
            });
        }).subscribe(subscriber);

        Util.sleep(Duration.ofSeconds(3));
        subscriber.getSubscription().request(2);
        Util.sleep(Duration.ofSeconds(3));
        subscriber.getSubscription().request(2);
        Util.sleep(Duration.ofSeconds(3));
        subscriber.getSubscription().cancel();
        subscriber.getSubscription().request(2);
    }
}
