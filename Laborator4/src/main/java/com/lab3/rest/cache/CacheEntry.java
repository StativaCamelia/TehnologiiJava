package com.lab3.rest.cache;

import com.lab3.model.Documents;

import java.util.Objects;
import java.util.Vector;

public class CacheEntry {

    String uri;
    Vector<Documents> response;

    public CacheEntry(Vector<Documents> response, String uri) {

        this.uri = uri;
        this.response = response;

    }

    public CacheEntry() {

    }

    public Vector<Documents> getResponse() {
        return response;
    }

    public void setResponse(Vector<Documents> response) {

        this.response = response;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CacheEntry entry = (CacheEntry) o;
        return Objects.equals(uri, entry.uri) && Objects.equals(response, entry.response);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uri, response);
    }
}
