package pg.reactive.reactiveprogramming.section03.assignment;

import pg.reactive.reactiveprogramming.common.Util;

import java.time.Duration;

public class Assignment {
    public static void main(String[] args) {
        var client = new Client();
        var subscriber =  new Subscriber();

        client.getPrices()
                .subscribe(subscriber);

        Util.sleep(Duration.ofSeconds(21));
    }
}
