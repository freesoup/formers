package formers.core.users;

import java.util.List;

import formers.core.database.Database;
import formers.core.exception.DatabaseException;
import formers.core.form.utils.FormFormat;
import formers.core.form.utils.FormID;
import formers.core.form.utils.FormResponse;

/**
 * Provides access to administrative user functionalities.
 * 
 * @author jackietan
 *
 */
public class AdminCore {
    private Database db;

    public AdminCore(Database db) {
        this.db = db;
    }

    public FormFormat initForm(String user) {
        FormFormat form = new FormFormat();
        form.addAdmin(user);
        form.addID(FormID.generateFormID());

        return form;
    }

    public boolean submitFormFormat(FormFormat form) {
        db.submitNewForm(form);
        return true;
    }

    public List<FormFormat> viewAllForm(String user) {
        List<FormFormat> formatList = db.getAllFormFormat(user);
        return formatList;
    }

    public FormResponse viewResult(String formID, String user) {
        FormResponse results = db.getFormResult(user, formID);
        return results;
    }

    public List<FormResponse> viewResultsOfAForm(String formID) {
        List<FormResponse> listResults = db.getAllFormResponse(formID);
        return listResults;
    }

    public void deleteAllTracesOf(String formIdToBeDeleted, String user) throws DatabaseException {
        db.deleteAllTracesOf(formIdToBeDeleted, user);
    }
}
