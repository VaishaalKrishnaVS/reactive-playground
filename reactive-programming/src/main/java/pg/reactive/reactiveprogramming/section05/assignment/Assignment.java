package pg.reactive.reactiveprogramming.section05.assignment;

import pg.reactive.reactiveprogramming.common.Util;

import java.time.Duration;

public class Assignment {
    public static void main(String[] args) {
        var client = new ExternalServiceClient();
        var service = new ProductServiceImpl(client);
        for(int i=1;i<=4;i++){
            service.getProductName(i)
                    .subscribe(Util.subscriber());
        }

        Util.sleep(Duration.ofSeconds(5));

    }
}
