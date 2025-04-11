package pg.reactive.reactiveprogramming.section02.assignment;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Slf4j
public class FileServiceImpl implements IFileService{
    private static final Path PATH = Path.of("/Users/zerno/Documents/reactive-playground/reactive-programming/src/main/resources/section02");
    @Override
    public Mono<String> read(String fileName) {
        return Mono.fromCallable(()->Files.readString(PATH.resolve(fileName)));
    }

    @Override
    public Mono<Void> write(String fileName, String content) {
        return  Mono.fromRunnable(()-> {
            try {
                Files.writeString(PATH.resolve(fileName),content);
                log.info("created file: {}", fileName);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Override
    public Mono<Void> delete(String file) {
        return Mono.fromRunnable(()-> {
            try {
                Files.delete(PATH.resolve(file));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

}
