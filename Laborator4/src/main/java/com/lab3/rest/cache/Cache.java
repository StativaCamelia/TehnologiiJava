package com.lab3.rest.cache;

import java.util.ArrayList;
import java.util.List;

public class Cache {

    private static Cache instance;
    private final List<CacheEntry> cache;

    private Cache() {

        cache = new ArrayList<>();
    }

    public static Cache getInstance() {
        if (instance == null) {
            instance = new Cache();

        }
        return instance;
    }

    public CacheEntry getEntry(String uri) {

        return cache.stream()
                .filter(ds -> uri.equals(ds.getUri()))
                .findFirst()
                .orElse(null);
    }

    public List<CacheEntry> getCache() {
        return cache;
    }

    public void addToCache(CacheEntry cacheEntry) {

        cache.add(cacheEntry);
    }

}
