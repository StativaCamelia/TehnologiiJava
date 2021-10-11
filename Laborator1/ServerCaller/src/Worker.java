import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Random;
import java.util.concurrent.Callable;

public class Worker implements Callable<Long> {

    Boolean sync;


    public Worker(Boolean sync){
        this.sync = sync;
    }

    @Override
    public Long call() throws Exception {
        long startTime = System.nanoTime();
        HttpClient client = HttpClient.newHttpClient();

        Random random = new Random();

        HttpRequest request = null;

        if(sync) {
             request = HttpRequest.newBuilder()
                    .uri(URI.create("http://localhost:8080/lab1-1.0-SNAPSHOT/myServlet?key=&value=0&sync=on"))
                    .build();
        }
        else{
            request = HttpRequest.newBuilder()
                    .uri(URI.create("http://localhost:8080/lab1-1.0-SNAPSHOT/myServlet?key=&value=0"))
                    .build();
        }

        client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .join();

        long endTime = System.nanoTime();
        long threadTime = endTime - startTime;

        return threadTime / 1000000000;
    }
}
