package com.lab3.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity(name = "resource")
@Table(name = "resource")
@NamedQueries({
        @NamedQuery(query = "Select r from resource r", name = "Resource.findAll"),
        @NamedQuery(query = "Select r.quantity from resource r where r.id=:id", name = "Resource.getQuantity"),
        @NamedQuery(query = "Select s.id from resource s", name = "Resource.getIds")
})
public class Resource {

    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @OneToMany(mappedBy = "resource")
    private List<ExamResource> exams = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public String getName() {
        return name;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setExams(List<ExamResource> exams) {
        this.exams = exams;
    }

    public List<ExamResource> getExams() {
        return exams;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Resource resource = (Resource) o;
        return Objects.equals(id, resource.id) && Objects.equals(name, resource.name) && Objects.equals(quantity, resource.quantity) && Objects.equals(exams, resource.exams);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, quantity, exams);
    }
}
