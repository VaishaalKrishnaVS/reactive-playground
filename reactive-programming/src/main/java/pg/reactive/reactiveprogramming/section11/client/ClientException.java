package pg.reactive.reactiveprogramming.section11.client;

public class ClientException extends RuntimeException {
    public ClientException() {
        super("bad request");
    }
}
