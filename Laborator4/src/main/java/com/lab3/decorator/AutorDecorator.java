package com.lab3.decorator;

import com.lab3.model.Documents;
import com.lab3.model.UserTypes;
import com.lab3.repo.DocumentsRepoBase;

import javax.decorator.Decorator;
import javax.decorator.Delegate;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.Cookie;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.ParseException;

@Decorator
public class AutorDecorator implements DocumentsRepoBase {

    @Inject
    @Delegate
    private DocumentsRepoBase decoratedObj;


    @Override
    public void saveDocument(Documents documents) throws UnsupportedEncodingException, ParseException {

        Cookie user = (Cookie) FacesContext.getCurrentInstance()
                .getExternalContext()
                .getRequestCookieMap().get("type");

        String type = URLDecoder.decode(user.getValue(), "UTF-8");

        if (type.equals(UserTypes.BASIC.getLabel()))
            decoratedObj.saveDocument(documents);
    }
}
