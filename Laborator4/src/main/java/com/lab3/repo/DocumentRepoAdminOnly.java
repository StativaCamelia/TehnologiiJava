package com.lab3.repo;

import com.lab3.model.Documents;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

public class DocumentRepoAdminOnly implements DocumentRepoAdminOnlyBase {

    @PersistenceContext(unitName = "JPAExample")
    private EntityManager em;

    @Override
    public List<Documents> readDocuments() {

        Query query = em.createNamedQuery("Documents.findAll");
        return query.getResultList();
    }
}
