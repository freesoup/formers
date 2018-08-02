package formers.core.users;

import formers.core.database.Database;
import formers.core.form.utils.FormResponse;
import formers.database.impl.DatabaseImpl;

/**
 * Provides access to normal user functionalities.
 * 
 * @author jackietan
 *
 */
public class UserCore {
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
