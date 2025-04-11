package pg.reactive.reactiveprogramming.section01.publisher;

import com.github.javafaker.Faker;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SubscriptionImpl implements Subscription {

    private static final Logger logger = LoggerFactory.getLogger(SubscriptionImpl.class);
    private final Faker faker;
    private final Subscriber<? super String> subscriber;
    private boolean isCancelled;
    private long counter = 0;
    private static final long MAX = 10;

    public SubscriptionImpl(Subscriber<? super String> subscriber) {
        this.subscriber = subscriber;
        this.faker = Faker.instance();
    }

    @Override
    public void request(long reqs) {
        if(isCancelled){
            return;
        }
        logger.info("requesting {} items", reqs);
        for(int i=0;i<reqs && counter<MAX;i++){
            subscriber.onNext(faker.harryPotter().quote());
            counter++;
        }
        if(counter == MAX){
            logger.info("publishing last item");
            subscriber.onComplete();
            isCancelled=true;
        }
    }

    @Override
    public void cancel() {
        logger.info("subscriber has cancelled");
        isCancelled = true;
    }
}
