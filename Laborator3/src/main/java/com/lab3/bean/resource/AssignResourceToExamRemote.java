package com.lab3.bean.resource;

import com.lab3.model.Exam;
import com.lab3.model.Resource;

import javax.ejb.Remote;
import java.util.Map;

@Remote
public interface AssignResourceToExamRemote {

    void initialize(Exam exam);

    void addResource(Resource resource, Integer quantity);

    void removeResource(Resource resource);

    Map<Resource, Integer> getContents();

    void remove();

    void assignResources();
}
