package pg.reactive.reactiveprogramming.section03;

import pg.reactive.reactiveprogramming.common.Util;

import static pg.reactive.reactiveprogramming.section03.helper.NameGenerator.getNamesFlux;
import static pg.reactive.reactiveprogramming.section03.helper.NameGenerator.getNamesList;

public class FluxVsList {
    public static void main(String[] args) {
        var list = getNamesList(10);
        System.out.println(list);

        getNamesFlux(10).subscribe(Util.subscriber());
    }
}
