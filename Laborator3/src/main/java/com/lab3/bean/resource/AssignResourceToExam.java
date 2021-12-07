package com.lab3.bean.resource;

import com.lab3.repo.ExamResourceRepo;

import javax.ejb.EJB;
import javax.ejb.Remove;
import javax.ejb.Stateful;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Stateful(mappedName = "reservation")
@Named("reservation")
public class AssignResourceToExam {

    Long examId;

    List<Long> resources;

    Long firstResource;

    Integer firstQuantity;

    Long secondResource;

    Integer secondQuantity;
    List<Integer> quantities;

    Map<Long, Integer> contents;


    @EJB
    private ExamResourceRepo examResourceRepo;

    public void initialize() {

        contents = new HashMap<>();
        resources = new ArrayList<Long>();
        quantities = new ArrayList<>();
    }

    public Long getExamId() {
        return examId;
    }

    public void setExamId(Long examId) {
        this.examId = examId;
    }

    public List<Integer> getQuantities() {
        return quantities;
    }

    public void setQuantities(List<Integer> quantities) {
        this.quantities = quantities;
    }

    public List<Long> getResources() {
        return resources;
    }

    public void setResources(List<Long> resources) {
        this.resources = resources;
    }

    @Remove
    public void remove() {

        contents = null;
    }

    public Integer getFirstQuantity() {
        return firstQuantity;
    }

    public Long getFirstResource() {
        return firstResource;
    }

    public Long getSecondResource() {
        return secondResource;
    }

    public Integer getSecondQuantity() {
        return secondQuantity;
    }

    public void setFirstQuantity(Integer firstQuantity) {
        this.firstQuantity = firstQuantity;
    }

    public void setFirstResource(Long firstResource) {
        this.firstResource = firstResource;
    }

    public void setSecondQuantity(Integer secondQuantity) {
        this.secondQuantity = secondQuantity;
    }

    public void setSecondResource(Long secondResource) {
        this.secondResource = secondResource;
    }

    public void assignResources() {
        if (firstQuantity != null) {

            quantities.add(firstQuantity);
        }
        if (secondQuantity != null) {

            quantities.add(secondQuantity);
        }

        if (firstResource != null) {

            resources.add(firstResource);
        }

        if (secondResource != null) {

            resources.add(secondResource);
        }

        if (resources != null && quantities != null) {
            contents = IntStream.range(0, resources.size())
                    .boxed()
                    .collect(Collectors.toMap(i -> resources.get(i), i -> quantities.get(i)));

            examResourceRepo.assign(examId, contents);
        }
    }

}
