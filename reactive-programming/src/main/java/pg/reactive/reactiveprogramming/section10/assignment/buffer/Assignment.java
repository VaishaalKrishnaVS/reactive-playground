package pg.reactive.reactiveprogramming.section10.assignment.buffer;

import pg.reactive.reactiveprogramming.common.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Assignment {
    public static void main(String[] args) {
        var allowedCategories = Set.of(
                "Science fiction",
                "Fantasy",
                "Suspense/Thriller"
        );

        orderStream().filter(bookOrder -> allowedCategories.contains(bookOrder.genre()))
                .buffer(Duration.ofSeconds(5))
                .map(Assignment::generateReport)
                .subscribe(Util.subscriber());

        Util.sleep(Duration.ofSeconds(60));
    }

    private static Flux<BookOrder> orderStream() {
        return Flux.interval(Duration.ofMillis(500))
                .map(i -> BookOrder.create());
    }

    private static RevenueReport generateReport(List<BookOrder> orders) {
        Map<String, Integer> map = orders.stream()
                .collect(Collectors.groupingBy(
                        BookOrder::genre,
                        Collectors.summingInt(BookOrder::price)));
        return new RevenueReport(LocalTime.now(), map);
    }
}
