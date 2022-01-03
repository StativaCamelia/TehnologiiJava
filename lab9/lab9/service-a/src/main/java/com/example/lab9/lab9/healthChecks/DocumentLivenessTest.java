package com.example.lab9.lab9.healthChecks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.json.JsonArray;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DocumentLivenessTest {

    private static HashMap<String, String> endpointData;
    private JsonArray servicesStates;
    private String HEALTH_ENDPOINT = "health";
    private String READINESS_ENDPOINT = "health/ready";
    private String LIVENES_ENDPOINT = "health/live";

    @BeforeEach
    public void setup() {
        endpointData = new HashMap<String, String>();
    }

    @Test
    public void testIfServicesAreUp() {
        endpointData.put("SystemResource Readiness Check", "UP");
        endpointData.put("SystemResource Liveness Check", "UP");
        endpointData.put("InventoryResource Readiness Check", "UP");
        endpointData.put("InventoryResource Liveness Check", "UP");

        servicesStates = HealthITUtil.connectToHealthEnpoint(200, HEALTH_ENDPOINT);
        checkStates(endpointData, servicesStates);
    }

    @Test
    public void testReadiness() {
        endpointData.put("SystemResource Readiness Check", "UP");
        endpointData.put("InventoryResource Readiness Check", "UP");

        servicesStates = HealthITUtil.connectToHealthEnpoint(200, READINESS_ENDPOINT);
        checkStates(endpointData, servicesStates);
    }

    @Test
    public void testLiveness() {
        endpointData.put("SystemResource Liveness Check", "UP");
        endpointData.put("InventoryResource Liveness Check", "UP");

        servicesStates = HealthITUtil.connectToHealthEnpoint(200, LIVENES_ENDPOINT);
        checkStates(endpointData, servicesStates);
    }

    private void checkStates(HashMap<String, String> testData, JsonArray servStates) {
        testData.forEach((service, expectedState) -> {
            assertEquals(expectedState, HealthITUtil.getActualState(service, servStates),
                    "The state of " + service + " service is not matching.");
        });
    }



}