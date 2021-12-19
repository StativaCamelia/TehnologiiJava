package com.lab3.rest.cache;

import com.lab3.model.Documents;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.util.Vector;

@Provider
public class CacheFilter implements ContainerRequestFilter, ContainerResponseFilter {

    private Cache cache;

    public CacheFilter() {
        this.cache = Cache.getInstance();
    }

    @Override
    public void filter(ContainerRequestContext requestContext) {

        if (requestContext.getMethod().equals("GET")) {

            CacheEntry entry = cache.getEntry(requestContext.getUriInfo().getPath());

            if (entry == null) {
            } else {

                Response response = Response.ok(entry.getResponse())
                        .type(MediaType.APPLICATION_JSON_TYPE).build();
                requestContext.abortWith(response);

            }
            return;
        } else {

            return;
        }
    }

    @Override
    public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext)
            throws IOException {

        System.out.println(responseContext);
        if (!requestContext.getMethod().equalsIgnoreCase("GET")) return;
        if (responseContext.getStatus() == 200) {

            if (cache.getEntry(requestContext.getUriInfo().getPath()) == null) {
                cache.addToCache(new CacheEntry((Vector<Documents>) responseContext.getEntity(), requestContext.getUriInfo().getPath()));
            }
            else{

                if(!cache.getEntry(requestContext.getUriInfo().getPath()).equals(responseContext.getEntity())){

                    cache.getEntry(requestContext.getUriInfo().getPath()).setResponse((Vector<Documents>) responseContext.getEntity());
                }
            }
        }
    }
}