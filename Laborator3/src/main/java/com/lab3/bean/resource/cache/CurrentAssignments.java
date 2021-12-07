package com.lab3.bean.resource.cache;

import com.lab3.model.ExamResource;
import com.lab3.repo.ExamResourceRepo;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Named;
import java.util.List;

@Singleton
@Startup
@Named("assignments")
public class CurrentAssignments {

    @EJB
    private ExamResourceRepo examResourceRepo;

    private List<ExamResource> examResources;

    @PostConstruct
    void init()
    {
        examResources = examResourceRepo.getExamResources();
    }


    public List<ExamResource> getExamResources() {

        return examResources;
    }


    public void setExamResources(List<ExamResource> examResources) {

        this.examResources = examResources;
    }

    public void addAssignment(ExamResource examResource) {

        examResources.add(examResource);
    }
}
