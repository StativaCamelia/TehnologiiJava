package com.example.lab9.lab9.healthChecks;

import com.example.lab9.lab9.DocumentController;
import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.Readiness;

import javax.enterprise.context.ApplicationScoped;


@Readiness
@ApplicationScoped
public class DocumentReadiness implements HealthCheck {
    @Override
    public HealthCheckResponse call() {
        return HealthCheckResponse
                .named(DocumentController.class.getSimpleName())
                .withData("ready", true).up().build();
    }
}
