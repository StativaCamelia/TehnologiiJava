package com.lab3.bean;

import com.lab3.model.*;
import com.lab3.repo.ExamRepo;
import org.primefaces.event.RowEditEvent;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Bean for the operations regarding a student
 */
@ManagedBean(name = "examBean")
@ViewScoped
public class ExamBean implements Serializable {


    @EJB
    ExamRepo examRepo;

    private Map<Long, Exam> examsAsMap;

    /**
     * Gets all the {@link Exam}s of presentation type from the database
     *
     * @return a list of {@link Exam}s
     */
    public List<Exam> getExamListPresentation() {

        return examRepo.getAllExamsPresentation();
    }

    public List<Exam> getExamListPresentationBySearchCriterias(SearchCriteria searchCriteria) {

        System.out.println(searchCriteria);
        return examRepo.getExamListPresentationBySearchCriterias(searchCriteria);
    }

    /**
     * Gets all the {@link Exam}s of presentation type from the database
     *
     * @return a list of {@link Exam}s
     */
    public List<Exam> getExamListWritten() {

        return examRepo.getAllExamsWritten();
    }

    /**
     * Gets the ids of the {@link Exam} saved in the database
     *
     * @return a list of ids
     */
    public List<Integer> getExamIds() {

        return examRepo.getExamsIds();
    }

    /**
     * Saves an {@link Exam} entity
     *
     * @param presentation The presentation that needs to be saved
     * @param time         The time for the presentation
     */
    public void saveExamPresentation(Presentation presentation, Time time) {

        int result = examRepo.insertExam(presentation, time);
        if (result != 0) {
            FacesContext.getCurrentInstance().addMessage("form:Create", new FacesMessage(FacesMessage.SEVERITY_INFO, "Exam Saved", "The presentation was successfully saved"));
        } else {
            FacesContext.getCurrentInstance().addMessage("form:Create", new FacesMessage(FacesMessage.SEVERITY_INFO, "Exam Not Saved", "The presentation was not saved"));
        }
    }

    public void saveExamWritten(Written written, Time time) {

        int result = examRepo.insertExam(written, time);
        if (result != 0) {
            FacesContext.getCurrentInstance().addMessage("form:Create", new FacesMessage(FacesMessage.SEVERITY_INFO, "Exam Saved", "The written was successfully saved"));
        } else {
            FacesContext.getCurrentInstance().addMessage("form:Create", new FacesMessage(FacesMessage.SEVERITY_INFO, "Exam Not Saved", "The written was not saved"));
        }
    }

    public void remove(Exam exam) {

        examRepo.deleteExam(exam);
    }

    public Map<Long, Exam> getExamsAsMap() {

        if (examsAsMap == null) {
            examsAsMap = getExamListPresentation().stream().collect(Collectors.toMap(Exam::getId, exam -> exam));
        }
        return examsAsMap;
    }

    public void onRowCancel() {
        FacesMessage msg = new FacesMessage("Edit Cancelled", "Edited");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onRowUpdate(RowEditEvent event) {

        Exam exam = (Exam) event.getObject();

        examRepo.update(exam);
        System.out.println(exam);
        FacesMessage msg = new FacesMessage("Product Edited", "Edited");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
}
