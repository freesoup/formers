package formers.core.users;

import formers.core.database.Database;
import formers.core.form.utils.FormResponse;

/**
 * Provides access to normal user functionalities.
 * 
 * @author jackietan
 *
 */
public class UserCore {
    private Database db;

    public UserCore(Database db) {
        this.db = db;
    }

    public boolean submitForm(FormResponse response) {
        db.submitResponse(response);
        return true;
    }

    public FormResponse initFormResponse(String userName) {
        FormResponse responses = new FormResponse(userName);
        return responses;
    }
}
