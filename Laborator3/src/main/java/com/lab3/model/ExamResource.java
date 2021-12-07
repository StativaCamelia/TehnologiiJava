package com.lab3.model;

import javax.persistence.*;
import java.util.Objects;

@Entity(name = "ExamResource")
@Table(name = "exam_resource")
@NamedQueries({
        @NamedQuery(query = "Select s from ExamResource s", name = "ExamResource.findAll")
})
public class ExamResource {

    @EmbeddedId
    private ExamResourceId examResourceId;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("examId")
    private Exam exam;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("resourceId")
    private Resource resource;

    @Column(name = "quantity")
    private Integer quantity;

    public ExamResource() {

    }

    public ExamResource(Exam exam, Resource resource, Integer quantity) {

        this.exam = exam;
        this.resource = resource;
        this.quantity = quantity;
    }

    public Exam getExam() {
        return exam;
    }

    public void setExam(Exam exam) {
        this.exam = exam;
    }

    public ExamResourceId getExamResourceId() {
        return examResourceId;
    }

    public void setExamResourceId(ExamResourceId examResourceId) {
        this.examResourceId = examResourceId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Resource getResource() {
        return resource;
    }

    public void setResource(Resource resource) {
        this.resource = resource;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ExamResource that = (ExamResource) o;
        return Objects.equals(examResourceId, that.examResourceId) && Objects.equals(exam, that.exam) && Objects.equals(resource, that.resource) && Objects.equals(quantity, that.quantity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(examResourceId, exam, resource, quantity);
    }
}
