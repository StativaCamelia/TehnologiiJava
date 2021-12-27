package com.lab3.rest;

import com.lab3.model.Documents;
import com.lab3.repo.DocumentsRepoBase;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/document")
@Api(value = "CRUD documents operation")
public class DocumentResource {

    private DocumentsRepoBase documentRepo;

    public DocumentResource() {

    }

    @Inject
    public DocumentResource(DocumentsRepoBase documentRepo) {

        this.documentRepo = documentRepo;
    }

    @GET
    @ApiOperation(value = "Get All the saved Documents by author")
    @Path("/{author}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Documents> viewDocumentByAuthor(@PathParam("author") String author) {

        return documentRepo.getDocuments(author);
    }

    @GET
    @ApiOperation(value = "Get All the saved Documents")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Documents> viewDocuments() {

        return documentRepo.getDocuments(null);
    }

    @POST
    @ApiOperation(value = "Saves a received document")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Documents saveDocument(Documents documents) {

        return documentRepo.saveDocuments(documents);
    }

    @DELETE
    @ApiOperation(value = "Deletes a documents by id")
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public void deleteDocument(@PathParam("id") int id) {
        documentRepo.delete(id);

    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Updates a document")
    public Documents update(Documents documents) {
        return documentRepo.update(documents);

    }
}
