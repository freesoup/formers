package formers.core.users;

import formers.core.database.Database;
import formers.core.database.DatabaseImpl;
import formers.core.form.utils.FormFormat;
import formers.core.form.utils.FormResponse;

/**
 * Provides access to normal user functionalities.
 * 
 * @author jackietan
 *
 */
public class UserCore {
    public FormFormat viewForm(String iD) {
        DatabaseImpl db = new DatabaseImpl();
        FormFormat form = db.getForm(iD);
        return form;
    }

    public boolean submitForm(FormResponse response) {
        Database db = new DatabaseImpl();
        db.submitResponse(response);
        return true;
    }

    public FormResponse initFormResponse(String userName) {
        FormResponse responses = new FormResponse(userName);
        return responses;
    }
}
