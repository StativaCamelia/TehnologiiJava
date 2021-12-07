package com.lab3.produces;

import javax.enterprise.inject.Produces;
import javax.faces.bean.ApplicationScoped;
import java.io.Serializable;

@ApplicationScoped
public class Generator implements Serializable {

    private static final long serialVersionUID = -7213673465118041882L;

    private final java.util.Random random =
            new java.util.Random(System.currentTimeMillis());

    private final int maxNumber = 100;

    java.util.Random getRandom() {
        return random;
    }

    @Produces
    @Random
    int next() {
        return getRandom().nextInt(maxNumber);
    }

}
