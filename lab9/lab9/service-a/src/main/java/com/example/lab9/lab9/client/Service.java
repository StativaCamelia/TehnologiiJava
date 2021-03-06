package com.example.lab9.lab9.client;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

@RegisterRestClient
@ApplicationScoped
public interface Service {

    @GET
    @Path("/{parameter}")
    String doSomething(@PathParam("parameter") String parameter);

}
