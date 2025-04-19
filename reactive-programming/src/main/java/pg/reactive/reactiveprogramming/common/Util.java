package pg.reactive.reactiveprogramming.common;

import com.github.javafaker.Faker;
import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Subscriber;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.function.UnaryOperator;

@Slf4j
public class Util {
    public static final Faker faker = Faker.instance();

    public static <T> Subscriber<T> subscriber() {
        return new DefaultSubscriber<>("");
    }

    public static <T> Subscriber<T> subscriber(String name) {
        return new DefaultSubscriber<>(name);
    }

    public static Faker faker() {
        return faker;
    }

    public static void sleep(Duration duration) {
        try {
            Thread.sleep(duration.toMillis());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> UnaryOperator<Flux<T>> fluxLogger(String name) {
        return tFlux ->
                tFlux.doOnSubscribe(subscription -> log.info("Subscribing to {}", name))
                        .doOnCancel(() -> log.info("Cancelling to {}", name))
                        .doOnComplete(() -> log.info("Completed subscriber :: {}", name));
    }
}
