package lambdasinaction.chap7;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.InvocationCallback;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;

/**
 * Created by disen on 05.07.16.
 */
public class ParalleRest {
    private static List<String> responses = new ArrayList<>();

    public static void main(String[] args) throws InterruptedException {
        Client client = ClientBuilder.newClient();

        long start = System.nanoTime();
        for (int i = 0; i < 10_000; i++)
            doAsyncRequest(client, i);

        while (responses.size() < 10_000) {
            //System.out.println("size " + responses.size());
        }
        long duration = (System.nanoTime() - start) / 1_000_000;
        System.out.println("Async in " + duration +" msecs");
        System.out.println("size " + responses.size());

        responses.clear();

        start = System.nanoTime();
        for (int i = 0; i < 10_000; i++)
            doRequest(client, i);
        duration = (System.nanoTime() - start) / 1_000_000;
        System.out.println("Sync in " + duration +" msecs");
        System.out.println("size " + responses.size());
    }

    static void doAsyncRequest(Client client, int n) {
        System.out.println("request " + n);

        final Future<Response> responseFuture = client.target("http://jsonplaceholder.typicode.com/posts/1")
                .request().async().get(new InvocationCallback<Response>() {
                    @Override
                    public void completed(Response response) {
                        System.out.println("Response status code "
                                + response.getStatus());
                        responses.add(response.readEntity(String.class));
                    }

                    @Override
                    public void failed(Throwable throwable) {
                        System.out.println("Invocation failed.");
                        throwable.printStackTrace();
                    }
                });

    }

    static void doRequest(Client client, int n) {
        System.out.println("request " + n);
        Response response = client.target("http://jsonplaceholder.typicode.com/posts/1").request().get();
        System.out.println("Response status code "
                + response.getStatus());
        responses.add(response.readEntity(String.class));
    }

}


