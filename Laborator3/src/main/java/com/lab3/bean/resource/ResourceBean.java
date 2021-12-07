package com.lab3.bean.resource;

import com.lab3.model.Resource;
import com.lab3.model.Student;
import com.lab3.repo.ResourceRepo;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.util.List;

@ManagedBean(name = "resourceBean")
@RequestScoped
public class ResourceBean {

    @EJB
    ResourceRepo resourceRepo;

    public List<Student> getResourceList() {

        return resourceRepo.getAllResources();
    }

    public void remove(Resource resource) {

        resourceRepo.deleteResource(resource);
    }

    public List<Integer> getResourceIds() {

        return resourceRepo.getResourcesIds();
    }

}
