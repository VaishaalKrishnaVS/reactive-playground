package pg.reactive.reactiveprogramming.section03.assignment;

import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Subscription;
@Slf4j
public class Subscriber implements org.reactivestreams.Subscriber<Integer> {
    private Subscription subscription;
    private int balance = 1000;
    private int quantity = 0;
    @Override
    public void onSubscribe(Subscription subscription) {
        this.subscription = subscription;
        subscription.request(Long.MAX_VALUE);
    }

    @Override
    public void onNext(Integer price) {
        if(price<90 && balance>=price){
            balance=balance-price;
            quantity++;
            log.info("bought a stock at {}. total quantity: {}, remaining balance: {}", price, quantity, balance);
        } else if (price>110 && quantity>0) {
            log.info("selling {} quantities at {}", quantity, price);
            balance = balance + (quantity * price);
            quantity = 0;
            subscription.cancel();
            log.info("profit: {}", (balance - 1000));
        }
    }

    @Override
    public void onError(Throwable throwable) {
        log.error("error: ", throwable);
    }

    @Override
    public void onComplete() {
        log.info("completed");
    }
}
