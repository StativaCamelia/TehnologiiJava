package com.lab3.repo;

import com.lab3.bean.resource.CheckAvailabilityRemote;
import com.lab3.model.Exam;
import com.lab3.model.ExamResource;
import com.lab3.model.Resource;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.Map;

@Stateless
public class ExamResourceRepo {

    @PersistenceContext(unitName = "JPAExample")
    private EntityManager em;

    @EJB
    private CheckAvailabilityRemote checkAvailabilityRemote;

    @EJB
    private ExamRepo examRepo;

    @EJB
    private ResourceRepo resourceRepo;

    public void assign(Long examId, Map<Long, Integer> resources) {

        if (examId == null) {
            return;
        } else {
            Exam exam = examRepo.getById(examId);

            if (resources != null) {
                for (Map.Entry<Long, Integer> resource : resources.entrySet()) {

                    Resource resource1 = resourceRepo.getById(resource.getKey());
                    ExamResource examResource = new ExamResource();
                    if (checkAvailabilityRemote.checkAvailability(new Map.Entry<Resource, Integer>() {
                        @Override
                        public Resource getKey() {
                            return resource1;
                        }

                        @Override
                        public Integer getValue() {
                            return resource.getValue();
                        }

                        @Override
                        public Integer setValue(Integer value) {
                            return null;
                        }
                    })) {

                        examResource.setExam(exam);
                        examResource.setResource(resource1);
                        examResource.setQuantity(resource.getValue());
                    }
                }
            }
        }
    }

    public List<ExamResource> getExamResources() {

        Query query = em.createNamedQuery("ExamResource.findAll");

        return query.getResultList();
    }
}
