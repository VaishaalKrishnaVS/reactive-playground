package pg.reactive.reactiveprogramming;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pg.reactive.reactiveprogramming.section01.publisher.PublisherImpl;
import pg.reactive.reactiveprogramming.section01.subscriber.SubscriberImpl;

import java.time.Duration;

/*
01. publisher does not produce data until subscriber requests for it.
02. publisher will only produce <= subscriber requested items.
03. subscriber can cancel the subscription at any time. producer should stop producing data at that moment.
04. producer can send error signal to the subscriber.
05. producer can send completion signal to the subscriber.
 */
public class Demo {
    private static final Logger logger = LoggerFactory.getLogger(Demo.class);

    public static void main(String[] args) throws InterruptedException {
        logger.info("Demo Case 1");
        demoCase1();
        logger.info("Demo Case 2");
        demoCase2();
        logger.info("Demo Case 3");
        demoCase3();
    }

    public static void demoCase1() {
        var publisher = new PublisherImpl();
        var subscriber = new SubscriberImpl();
        publisher.subscribe(subscriber);
    }

    public static void demoCase2() throws InterruptedException {
        var publisher = new PublisherImpl();
        var subscriber = new SubscriberImpl();
        publisher.subscribe(subscriber);
        subscriber.getSubscription().request(3);
        Thread.sleep(Duration.ofSeconds(2));
        subscriber.getSubscription().request(3);
        Thread.sleep(Duration.ofSeconds(2));
        subscriber.getSubscription().request(3);
        Thread.sleep(Duration.ofSeconds(2));
        subscriber.getSubscription().request(3);
        Thread.sleep(Duration.ofSeconds(2));
    }

    public static void demoCase3() throws InterruptedException {
        var publisher = new PublisherImpl();
        var subscriber = new SubscriberImpl();
        publisher.subscribe(subscriber);
        subscriber.getSubscription().request(3);
        Thread.sleep(Duration.ofSeconds(2));
        subscriber.getSubscription().cancel();
        subscriber.getSubscription().request(3);
    }
}
