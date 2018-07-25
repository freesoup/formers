package formers.core.users;

import java.util.List;

import formers.core.database.DatabaseImpl;
import formers.core.form.utils.FormFormat;
import formers.core.form.utils.FormResponse;
import formers.core.form.utils.Response;

/**
 * Provides access to normal user functionalities.
 * 
 * @author jackietan
 *
 */
public class UserCore {
    public FormFormat viewForm(String ID) {
        DatabaseImpl db = new DatabaseImpl();
        FormFormat form = db.getForm(ID);
        return form;
    }

    public void submitForm(FormFormat form, List<Response> responseList) {
        DatabaseImpl db = new DatabaseImpl();
        FormResponse responses = new FormResponse(form);

        for (Response answer : responseList) {
            responses.addResponse(answer);
        }

        db.submitResponse(responses);
    }
}
