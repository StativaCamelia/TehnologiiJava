package com.example.lab9.lab9.healthChecks;


import com.example.lab9.lab9.DocumentController;
import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.Liveness;

import javax.enterprise.context.ApplicationScoped;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;

@Liveness
@ApplicationScoped
public class DocumentLiveness implements HealthCheck {


    @Override
    public HealthCheckResponse call() {
        MemoryMXBean memBean = ManagementFactory.getMemoryMXBean();
        long memUsed = memBean.getHeapMemoryUsage().getUsed();
        long memMax = memBean.getHeapMemoryUsage().getMax();

        return HealthCheckResponse
                .named(DocumentController.class.getSimpleName())
                .withData("memory used", memUsed)
                .withData("memory max", memMax)
                .status(memUsed < memMax * 0.9).build();
    }
}
