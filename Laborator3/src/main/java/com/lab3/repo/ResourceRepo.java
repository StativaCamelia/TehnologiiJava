package com.lab3.repo;

import com.lab3.model.Resource;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.Map;

@Stateless
public class ResourceRepo {

    @PersistenceContext(unitName = "JPAExample")
    private EntityManager em;

    public List getAllResources() {

        Query query = em.createNamedQuery("Resource.findAll");
        return query.getResultList();
    }

    public List getResourcesIds() {

        Query query = em.createNamedQuery("Resource.getIds");
        return query.getResultList();
    }

    public Resource getById(Long resourceId) {

        return em.find(Resource.class, resourceId);
    }

    public void deleteResource(Resource resource) {

        if (!em.contains(resource)) {
            resource = em.merge(resource);
        }

        em.remove(resource);
    }


    public boolean check(Map.Entry<Resource, Integer> resource) {

        Query query = em.createNamedQuery("Resource.getQuantity");
        query.setParameter("id", resource.getKey().getId());

        return (Integer) query.getSingleResult() - resource.getValue() > 0;
    }
}
