package com.my;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

/**
 * Created by disen on 11.07.16.
 */
public class JerseyRestCleint {

    private List<String> responses = new ArrayList<>();

    public JerseyRestCleint(Supplier<WebTarget> webTargetSupplier){
        WebTarget wt = webTargetSupplier.get();

        Client client = ClientBuilder.newClient();
        Response response = client.target("http://jsonplaceholder.typicode.com/posts/1").request().get();
        System.out.println("Response status code "
                + response.getStatus());
        responses.add(response.readEntity(String.class));
    }

    public List<String> getResponses(){
        return responses;
    }


}
