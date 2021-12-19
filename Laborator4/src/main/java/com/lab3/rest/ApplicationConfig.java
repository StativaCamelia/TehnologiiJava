package com.lab3.rest;

import com.lab3.rest.cache.CacheFilter;
import io.swagger.jaxrs.config.BeanConfig;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.Set;

@ApplicationPath("resources")
@ApplicationScoped
public class ApplicationConfig extends Application {

    public ApplicationConfig() {

        BeanConfig beanConfig = new BeanConfig();
        beanConfig.setSchemes(new String[]{"http"});
        beanConfig.setBasePath("/resources");
        beanConfig.setResourcePackage("com.lab3.rest");
        beanConfig.setPrettyPrint(true);
        beanConfig.setScan(true);
    }

    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(DocumentResource.class);
    }

    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        resources.add(io.swagger.jaxrs.listing.ApiListingResource.class);
        resources.add(io.swagger.jaxrs.listing.SwaggerSerializers.class);
        resources.add(CacheFilter.class);
        return resources;
    }
}
