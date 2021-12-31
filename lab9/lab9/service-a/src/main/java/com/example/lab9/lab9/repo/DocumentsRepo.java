package com.example.lab9.lab9.repo;

import com.example.lab9.lab9.model.Documents;

import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@RequestScoped
public class DocumentsRepo {

    @PersistenceContext(unitName = "JPA")
    private EntityManager em;

    @Transactional
    public void saveDocument(Documents documents) {

        em.persist(documents);
    }

    @Transactional
    public List getDocuments(String author) {

        Query query;

        if (author != null) {
            query = em.createNamedQuery("Documents.findAllByAuthor");
            query.setParameter("author", author);
        } else {
            query = em.createNamedQuery("Documents.findAll");
        }
        return query.getResultList();
    }

    @Transactional
    public Documents saveDocuments(Documents documents) {

        em.persist(documents);
        return documents;
    }

    @Transactional
    public void delete(long id) {

        Documents documents = em.find(Documents.class, id);
        em.remove(documents);
    }

    @Transactional
    public Documents update(Documents documents) {

        em.merge(documents);
        return documents;
    }
}
