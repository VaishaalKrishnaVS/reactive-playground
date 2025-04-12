package pg.reactive.reactiveprogramming.section04.assignment;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.SynchronousSink;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;

@Slf4j
public class FileReaderServiceImpl implements IFileReaderService{
    @Override
    public Flux<String> read(Path path) {
        return Flux.generate(
                ()->openFile(path),
                this::readFile,
                this::closeFile
        );
    }
    private BufferedReader openFile(Path path) throws IOException {
        log.info("opening file");
        return Files.newBufferedReader(path);
    }
    private BufferedReader readFile(BufferedReader reader, SynchronousSink<String> sink){
        try {
            var line = reader.readLine();
            log.info("reading file");
            if(Objects.isNull(line)) sink.complete();
            else sink.next(line);
        } catch (IOException e) {
            sink.error(e);
        }
        return reader;
    }
    private void closeFile(BufferedReader reader){
        try {
            reader.close();
            log.info("closing file");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
