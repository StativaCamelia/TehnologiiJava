package com.lab3.bean;

import com.lab3.log.Log;
import com.lab3.model.Documents;
import com.lab3.observer.EventSubmit;
import com.lab3.produces.Random;
import com.lab3.repo.DocumentsRepoBase;
import org.primefaces.model.file.UploadedFile;

import javax.enterprise.event.Event;
import javax.enterprise.inject.Instance;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.Cookie;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.ParseException;

@Log
@ManagedBean(name = "submitFile")
@SessionScoped
public class SubmitFile {

    @Inject
    DocumentsRepoBase documentsRepoBase;

    @Random
    @Inject
    Instance<Integer> randomInt;

    @Inject
    Event<EventSubmit> event;

    private transient UploadedFile uploadedFile;

    public UploadedFile getUploadedFile() {
        return uploadedFile;
    }

    public void setUploadedFile(UploadedFile uploadedFile) {
        this.uploadedFile = uploadedFile;
    }

    public void submitDocument() throws UnsupportedEncodingException, ParseException {

        Cookie user = (Cookie) FacesContext.getCurrentInstance()
                .getExternalContext()
                .getRequestCookieMap().get("user");

        String author = URLDecoder.decode(user.getValue(), "UTF-8");

        documentsRepoBase.saveDocument(new Documents(randomInt.get(), author, uploadedFile.getFileName(), uploadedFile.getContent()));

        event.fire(new EventSubmit("File submitted"));
    }

}
