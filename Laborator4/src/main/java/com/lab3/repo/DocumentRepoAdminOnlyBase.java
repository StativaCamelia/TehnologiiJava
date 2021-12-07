package com.lab3.repo;

import com.lab3.model.Documents;

import java.io.UnsupportedEncodingException;
import java.util.List;

public interface DocumentRepoAdminOnlyBase {

    List<Documents> readDocuments() throws UnsupportedEncodingException;
}
