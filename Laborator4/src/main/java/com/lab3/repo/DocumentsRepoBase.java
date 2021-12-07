package com.lab3.repo;

import com.lab3.model.Documents;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;

public interface DocumentsRepoBase {

    void saveDocument(Documents documents) throws UnsupportedEncodingException, ParseException;
}
