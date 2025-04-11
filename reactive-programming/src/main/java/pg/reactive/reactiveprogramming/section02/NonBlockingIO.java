package pg.reactive.reactiveprogramming.section02;

import lombok.extern.slf4j.Slf4j;
import pg.reactive.reactiveprogramming.common.Util;
import pg.reactive.reactiveprogramming.section02.client.ExternalServiceClient;

import java.time.Duration;

@Slf4j
public class NonBlockingIO {
    public static void main(String[] args) {
        var client = new ExternalServiceClient();
        log.info("Starting the reqs");
        for(int i=1;i<=100;i++){
            client.getProductName(i).subscribe(Util.subscriber());
        }
        Util.sleep(Duration.ofSeconds(3));
    }
}
