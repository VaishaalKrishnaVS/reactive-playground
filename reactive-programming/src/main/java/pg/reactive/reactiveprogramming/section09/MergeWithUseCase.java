package pg.reactive.reactiveprogramming.section09;

import pg.reactive.reactiveprogramming.common.Util;
import pg.reactive.reactiveprogramming.section09.helper.aero.Qayak;

import java.time.Duration;

public class MergeWithUseCase {
    public static void main(String[] args) {
        Qayak.getFlights()
                .subscribe(Util.subscriber());

        Util.sleep(Duration.ofSeconds(5));
    }
}
