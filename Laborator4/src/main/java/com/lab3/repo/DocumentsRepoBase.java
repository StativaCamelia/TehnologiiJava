package com.lab3.repo;

import com.lab3.model.Documents;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.util.List;

public interface DocumentsRepoBase {

    void saveDocument(Documents documents) throws UnsupportedEncodingException, ParseException;

    List<Documents> getDocuments(String autor);

    Documents saveDocuments(Documents documents);

    void delete(long id);

    Documents update(Documents documents);
}
