package pg.reactive.reactiveprogramming.common;

import reactor.netty.http.client.HttpClient;
import reactor.netty.resources.LoopResources;

public abstract class AbstractHttpClient {
    protected static final String BASE_URL = "http://localhost:7070";
    protected final HttpClient httpClient;

    protected AbstractHttpClient() {
        var loopResources = LoopResources.create("vs", 1,true);
        this.httpClient = HttpClient.create().runOn(loopResources).baseUrl(BASE_URL);
    }
}
