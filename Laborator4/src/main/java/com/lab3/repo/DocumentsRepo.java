package com.lab3.repo;

import com.lab3.log.LogFiles;
import com.lab3.model.Documents;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@LogFiles
public class DocumentsRepo implements DocumentsRepoBase {

    @PersistenceContext(unitName = "JPAExample")
    private EntityManager em;

    @Transactional
    @Override
    public void saveDocument(Documents documents) {

        em.persist(documents);
    }
}
