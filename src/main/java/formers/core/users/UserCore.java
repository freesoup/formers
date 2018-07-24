package formers.core.users;

import java.util.List;

import formers.core.form.utils.FormFormat;
import formers.core.form.utils.FormResponse;
import formers.core.form.utils.Response;
import formers.core.service.Database;

/**
 * Provides access to normal user functionalities.
 * 
 * @author jackietan
 *
 */
public class UserCore {
    public FormFormat viewForm(String ID) {
        Database db = new Database();
        FormFormat form = db.getForm(ID);
        return form;
    }

    public void submitForm(FormFormat form, List<Response> responseList) {
        Database db = new Database();
        FormResponse responses = new FormResponse(form);

        for (Response answer : responseList) {
            responses.addResponse(answer);
        }

        db.submitResponse(responses);
    }
}
