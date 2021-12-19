package com.lab3.repo;

import com.lab3.model.Documents;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

public class DocumentsRepo implements DocumentsRepoBase {

    @PersistenceContext(unitName = "JPAExample")
    private EntityManager em;

    @Transactional
    @Override
    public void saveDocument(Documents documents) {

        em.persist(documents);
    }

    @Transactional
    @Override
    public List getDocuments(String author){

        Query query;

        if(author != null) {
            query = em.createNamedQuery("Documents.findAllByAuthor");
            query.setParameter("author", author);
        }
        else{
            query = em.createNamedQuery("Documents.findAll");
        }
        return query.getResultList();
    }

    @Transactional
    @Override
    public Documents saveDocuments(Documents documents) {

        em.persist(documents);
        return documents;
    }

    @Transactional
    @Override
    public void delete(long id) {

        Documents documents = em.find(Documents.class, id);
        em.remove(documents);
    }

    @Transactional
    @Override
    public Documents update(Documents documents) {

        em.merge(documents);
        return documents;
    }
}
