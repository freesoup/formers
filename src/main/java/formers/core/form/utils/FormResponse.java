package formers.core.form.utils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * A response to a form that has been submitted by a Normal User.
 * 
 * @author jackietan
 *
 */
public class FormResponse {
    private String formID;
    private String recipient;
    private Date dateOfSubmission;
    private List<Response> responses;

    public FormResponse(String user) {
        this.recipient = user;
        this.dateOfSubmission = new Date();
        responses = new ArrayList<Response>();
    }

    public void setFormID(String iD) {
        this.formID = iD;
    }

    public void addResponse(Response answer) {
        this.responses.add(answer);
    }

    public String getFormID() {
        return this.formID;
    }

    public String getRecipient() {
        return this.recipient;
    }

    public String getDateOfSubmissionInString() {
        return DateParser.ParseDateToHTMLString(dateOfSubmission);
    }

    public Date getDate() {
        return dateOfSubmission;
    }

    public List<Response> getResponses() {
        return responses;
    }
}
