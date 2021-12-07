package com.lab3.bean.resource;

import com.lab3.model.Resource;
import com.lab3.repo.ResourceRepo;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.Map;

@Stateless
public class CheckAvailabilityBean implements CheckAvailabilityRemote {

    @EJB
    ResourceRepo resourceRepo;

    @Override
    public boolean checkAvailability(Map.Entry<Resource, Integer> resource) {

        return resourceRepo.check(resource);
    }
}
