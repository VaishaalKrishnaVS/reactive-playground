package pg.reactive.reactiveprogramming.section05;

import lombok.extern.slf4j.Slf4j;
import pg.reactive.reactiveprogramming.common.Util;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
public class ErrorHandling {
    public static void main(String[] args) {
        demoOnErrorComplete();
        demoOnErrorReturn();
        demoOnErrorResume();
        demoOnErrorContinue();
    }
    // skip the error and continue
    private static void demoOnErrorContinue(){
        Flux.range(1, 10)
                .map(i -> i == 5 ? 5 / 0 : i) // intentional
                .onErrorContinue((ex, obj) -> log.error("==> {}", obj, ex))
                .subscribe(Util.subscriber());
    }
    private static void demoOnErrorComplete(){
        Flux.range(1,1)
                .map(integer -> integer/0)
                .onErrorComplete()
                .subscribe();
    }
    // when you want to return a hardcoded value / simple computation
    private static void demoOnErrorReturn() {
        Mono.just(5)
                .map(i -> i == 5 ? 5 / 0 : i) // intentional
                .onErrorReturn(IllegalArgumentException.class, -1)
                .onErrorReturn(ArithmeticException.class, -2)
                .onErrorReturn(-3)
                .subscribe(Util.subscriber());
    }
    // when you have to use another publisher in case of error
    private static void demoOnErrorResume() {
        Mono.error(new RuntimeException("oops"))
                .onErrorResume(ArithmeticException.class, ex -> fallback1())
                .onErrorResume(ex -> fallback2())
                .onErrorReturn(-5)
                .subscribe(Util.subscriber());
    }

    private static Mono<Integer> fallback1() {
        return Mono.fromSupplier(() -> Util.faker().random().nextInt(10, 100));
    }

    private static Mono<Integer> fallback2() {
        return Mono.fromSupplier(() -> Util.faker().random().nextInt(100, 1000));
    }
}
