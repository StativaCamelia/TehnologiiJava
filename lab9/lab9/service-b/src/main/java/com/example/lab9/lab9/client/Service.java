package com.example.lab9.lab9.client;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

@RegisterRestClient(baseUri = "http://localhost:9080/data/document")
@ApplicationScoped
public interface Service {

    @GET
    @Path("/{author}")
    String getDocument(@PathParam("author") String parameter);

}
