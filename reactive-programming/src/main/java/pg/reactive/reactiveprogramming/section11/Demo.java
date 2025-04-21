package pg.reactive.reactiveprogramming.section11;

import lombok.extern.slf4j.Slf4j;
import pg.reactive.reactiveprogramming.common.Util;
import pg.reactive.reactiveprogramming.section11.client.ExternalServiceClient;
import pg.reactive.reactiveprogramming.section11.client.ServerException;
import reactor.util.retry.Retry;

import java.time.Duration;

@Slf4j
public class Demo {
    public static void main(String[] args) {
        retry();
        repeat();
        Util.sleep(Duration.ofSeconds(10));
    }
    private static void repeat(){
        var client = new ExternalServiceClient();
        client.getCountry()
                .repeat()
                .takeUntil(s -> s.equalsIgnoreCase("canada"))
                .subscribe(Util.subscriber());
    }

    private static void retry(){
        var client = new ExternalServiceClient();
        client.getProductName(2)
                .retryWhen(retryOnServerError())
                .subscribe(Util.subscriber());
    }

    private static Retry retryOnServerError(){
        return Retry.fixedDelay(5, Duration.ofMillis(500))
                .filter(throwable -> ServerException.class.equals(throwable.getClass()))
                .doBeforeRetry(retrySignal -> log.info("retrying.. {}",retrySignal.failure().getMessage()));
    }
}
