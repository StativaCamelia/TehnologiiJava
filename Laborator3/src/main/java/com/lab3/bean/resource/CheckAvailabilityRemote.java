package com.lab3.bean.resource;

import com.lab3.model.Resource;

import javax.ejb.Remote;
import java.util.Map;

@Remote
public interface CheckAvailabilityRemote {

    boolean checkAvailability(Map.Entry<Resource, Integer> resource);
}
