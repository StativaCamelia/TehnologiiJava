package com.lab3.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ExamResourceId implements Serializable {

    @Column(name = "exam_id")
    private Long examId;

    @Column(name = "resource_id")
    private Long resourceId;

    public ExamResourceId(){

    }

    public ExamResourceId(Long examId, Long resourceId) {

        this.examId = examId;
        this.resourceId = resourceId;
    }

    public Long getExamId() {
        return examId;
    }

    public void setExamId(Long examId) {
        this.examId = examId;
    }

    public Long getResourceId() {
        return resourceId;
    }

    public void setResourceId(Long resourceId) {
        this.resourceId = resourceId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ExamResourceId that = (ExamResourceId) o;
        return Objects.equals(examId, that.examId) && Objects.equals(resourceId, that.resourceId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(examId, resourceId);
    }
}
