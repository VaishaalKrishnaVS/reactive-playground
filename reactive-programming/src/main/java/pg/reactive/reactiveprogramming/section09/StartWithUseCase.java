package pg.reactive.reactiveprogramming.section09;

import pg.reactive.reactiveprogramming.common.Util;
import pg.reactive.reactiveprogramming.section09.helper.NameGenerator;

public class StartWithUseCase {
    public static void main(String[] args) {
        var nameGenerator = new NameGenerator();

        nameGenerator.generateNames()
                .take(2)
                .subscribe(Util.subscriber("sub01"));

        nameGenerator.generateNames()
                .take(2)
                .subscribe(Util.subscriber("sub02"));

        nameGenerator.generateNames()
                .take(4)
                .subscribe(Util.subscriber("sub03"));
    }
}
