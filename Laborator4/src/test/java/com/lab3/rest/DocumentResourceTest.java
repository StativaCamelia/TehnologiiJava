package com.lab3.rest;

import com.lab3.repo.DocumentsRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static io.restassured.RestAssured.expect;
import static org.hamcrest.Matchers.equalTo;

class DocumentResourceTest {

    @Mock
    DocumentsRepo documentsRepo;


    @BeforeEach
    public void setUp(){
    }

    @Test
    void viewDocument() {
        expect().
                body("id", equalTo("12")).
                body("firstName", equalTo("Tim")).
                body("lastName", equalTo("Tester")).
                body("birthday", equalTo("1970-01-16T07:56:49.871+01:00")).
                when().
                get("/resources/document");
    }
}