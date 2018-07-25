package formers.core.users;

import java.util.List;

import formers.core.database.Database;
import formers.core.database.DatabaseImpl;
import formers.core.form.utils.FormFormat;
import formers.core.form.utils.FormID;
import formers.core.form.utils.FormResult;

/**
 * Provides access to administrative user functionalities.
 * 
 * @author jackietan
 *
 */
public class AdminCore {
    String accountName;

    public AdminCore() {
        this.accountName = "Tom";
    }

    public FormFormat initForm() {
        FormFormat form = new FormFormat();
        form.addAdmin(accountName);
        form.addID(FormID.generateFormID());

        return form;
    }

    public boolean submitFormFormat(FormFormat form) {
        Database db = new DatabaseImpl();
        db.submitNewForm(form);
        return true;
    }

    public List<String> viewAllForm() {
        Database db = new DatabaseImpl();
        List<String> idList = db.getAllFormId();
        return idList;
    }

    public FormFormat viewForm(String ID) {
        Database db = new DatabaseImpl();
        FormFormat form = db.getForm(ID);
        return form;
    }

    public FormResult viewResult(String ID) {
        Database db = new DatabaseImpl();
        FormResult results = db.getFormResult(ID);
        return results;
    }
}
