package pg.reactive.reactiveprogramming.section04.assignment;

import pg.reactive.reactiveprogramming.common.Util;

import java.nio.file.Path;

public class Assignment {
    public static void main(String[] args) {
        var path = Path.of("reactive-programming/src/main/resources/section04/file.txt");
        var fileReader = new FileReaderServiceImpl();
        fileReader.read(path)
                .take(20)
                .subscribe(Util.subscriber());
    }
}
