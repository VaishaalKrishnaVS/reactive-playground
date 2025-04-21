package pg.reactive.reactiveprogramming.section11.client;

public class ServerException extends RuntimeException {
    public ServerException() {
        super("server error");
    }
}
