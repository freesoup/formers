package formers.core.object;

import java.util.List;

/**
 * A response to a form that has been submitted by a Normal User.
 * 
 * @author jackietan
 *
 */
public class FormResponse {
    private FormFormat formResponseTo;
    private List<Response> responses;

    public FormResponse(FormFormat form) {
        this.formResponseTo = form;
    }

    public void addResponse(Response answer) {
        this.responses.add(answer);
    }
}
