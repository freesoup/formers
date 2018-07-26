package formers.core.users;

import java.util.List;

import formers.core.database.Database;
import formers.core.database.DatabaseImpl;
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
    public AdminCore() {
    }

    public FormFormat initForm(String user) {
        FormFormat form = new FormFormat();
        form.addAdmin(user);
        form.addID(FormID.generateFormID());

        return form;
    }

    public boolean submitFormFormat(FormFormat form) {
        Database db = new DatabaseImpl();
        db.submitNewForm(form);
        return true;
    }

    public List<FormFormat> viewAllForm(String user) {
        Database db = new DatabaseImpl();
        List<FormFormat> formatList = db.getAllFormFormat(user);
        return formatList;
    }

    public FormFormat viewForm(String formID) {
        Database db = new DatabaseImpl();
        FormFormat form = db.getForm(formID);
        return form;
    }

    public FormResponse viewResult(String formID, String user) {
        Database db = new DatabaseImpl();
        FormResponse results = db.getFormResult(user, formID);
        return results;
    }
}
