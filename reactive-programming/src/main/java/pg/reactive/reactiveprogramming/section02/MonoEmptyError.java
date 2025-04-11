package pg.reactive.reactiveprogramming.section02;

import pg.reactive.reactiveprogramming.common.Util;
import reactor.core.publisher.Mono;

public class MonoEmptyError {
    public static void main(String[] args) {
        repository(3).subscribe(Util.subscriber());
    }
    private static Mono<String> repository(int userId) {
        if(userId == 1) {
            return Mono.just("Test");
        } else if(userId == 2) {
            return Mono.empty();
        } else {
            return Mono.error(new RuntimeException("Not allowed"));
        }
    }
}
