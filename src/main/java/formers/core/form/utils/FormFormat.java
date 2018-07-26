package formers.core.form.utils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * Class to represent the layout of a form. To be used when creating/populating a form, as well as viewing the details
 * of a form.
 * 
 * @author jackietan
 *
 */
public class FormFormat {
    private String formTitle;
    private String preamble;
    private String ID;
    private String owner;
    private Date dateCreated;
    private Date dateExpiry;
    private List<Question> questions;

    public FormFormat() {
        this.dateCreated = new Date();
        this.questions = new ArrayList<Question>();
    }

    public void addTitle(String info) {
        this.formTitle = info;
    }

    public void addPreamble(String info) {
        this.preamble = info;
    }

    public void addID(String id) {
        this.ID = Objects.requireNonNull(id, "Fatal Error encountered assigning ID to the form.");
    }

    public void addAdmin(String owner) {
        this.owner = owner;
    }

    public void setDateCreated(Date date) {
        this.dateCreated = date;
    }

    public void setDateExpiry(Date date) {
        this.dateExpiry = date;
    }

    public void addQuestion(Question field) {
        questions.add(field);
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public String getPreamble() {
        return preamble;
    }

    public String getTitle() {
        return formTitle;
    }
}
