package com.example.lab9.lab9;

import com.example.lab9.lab9.model.Documents;
import com.example.lab9.lab9.repo.DocumentsRepo;
import org.eclipse.microprofile.faulttolerance.*;
import org.eclipse.microprofile.metrics.annotation.Counted;
import org.eclipse.microprofile.metrics.annotation.Timed;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

@Path("/document")
public class DocumentController {

    @Inject
    private DocumentsRepo documentRepo;

    public DocumentController() {

    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Asynchronous
    @Bulkhead(value = 5, waitingTaskQueue = 8)
    public Future<Documents> saveDocument(Documents documents) {

        return CompletableFuture.completedFuture(documentRepo.saveDocuments(documents));
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public void deleteDocument(@PathParam("id") int id) {
        documentRepo.delete(id);

    }

    @GET
    @Path("/{author}")
    @Produces(MediaType.APPLICATION_JSON)
    @Timed(name = "view Document time",
            tags = {"method=get"},
            absolute = true,
            description = "Time needed to view docuemnts")
    @Counted(name = "view document count",
            absolute = true,
            description = "Number of times the view documents is called")
    @Retry(maxRetries = 2, delay = 200, jitter = 50)
    @Fallback(fallbackMethod = "fallback")
    public List<Documents> viewDocumentByAuthor(@PathParam("author") String author) {

        return documentRepo.getDocuments(author);
    }


    private List<Documents> fallback(String author) {
        return Collections.emptyList();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Bulkhead(5)
    public List<Documents> viewDocuments() {

        return documentRepo.getDocuments(null);
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @CircuitBreaker(successThreshold = 10, requestVolumeThreshold = 4, failureRatio = 0.75, delay = 1000)
    public Documents update(Documents documents) {
        return documentRepo.update(documents);

    }
}
