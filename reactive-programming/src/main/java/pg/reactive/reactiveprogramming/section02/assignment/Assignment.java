package pg.reactive.reactiveprogramming.section02.assignment;

import pg.reactive.reactiveprogramming.common.Util;

public class Assignment {
    public static void main(String[] args) {
        var fileService = new FileServiceImpl();

        fileService.write("file.txt", "This is a demo file")
                .subscribe(Util.subscriber());
        fileService.read("file.txt")
                .subscribe(Util.subscriber());
        fileService.delete("file.txt")
                .subscribe(Util.subscriber());
    }
}
