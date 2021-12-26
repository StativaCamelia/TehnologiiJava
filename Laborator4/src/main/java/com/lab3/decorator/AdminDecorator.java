package com.lab3.decorator;

import com.lab3.model.Documents;
import com.lab3.repo.DocumentRepoAdminOnlyBase;

import javax.decorator.Decorator;
import javax.decorator.Delegate;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.Cookie;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;

@Decorator
public class AdminDecorator implements DocumentRepoAdminOnlyBase {

    @Inject
    @Delegate
    private DocumentRepoAdminOnlyBase documentRepoAdminOnlyBase;

    @Override
    public List<Documents> readDocuments() throws UnsupportedEncodingException {
        Cookie user = (Cookie) FacesContext.getCurrentInstance()
                .getExternalContext()
                .getRequestCookieMap().get("type");

        String type = URLDecoder.decode(user.getValue(), "UTF-8");
        return documentRepoAdminOnlyBase.readDocuments();
//        if (type.equals(UserTypes.ADMIN.getLabel())) {

//        } else {
//            return Collections.emptyList();
//        }
    }
}
