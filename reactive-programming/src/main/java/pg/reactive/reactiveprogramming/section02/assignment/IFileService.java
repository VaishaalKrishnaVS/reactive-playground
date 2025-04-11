package pg.reactive.reactiveprogramming.section02.assignment;

import reactor.core.publisher.Mono;

public interface IFileService {
    Mono<String> read(String fileName);
    Mono<Void> write(String fileName, String content);
    Mono<Void> delete(String file);

}
