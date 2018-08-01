package formers.core.users;

import java.util.List;

import formers.core.database.Database;
import formers.core.database.DatabaseImpl;
import formers.core.form.utils.FormFormat;
import formers.core.form.utils.FormID;
import formers.core.form.utils.FormResponse;

public class Player {
    private AdminCore admin;
    private UserCore user;

    public Player() {
        admin = new AdminCore();
        user = new UserCore();
    }

    // For testing using Mockito
    public Player(AdminCore admin, UserCore user) {
        this.admin = admin;
        this.user = user;
    }

    // Admin specific functions
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

    public FormResponse viewResult(String formID, String user) {
        Database db = new DatabaseImpl();
        FormResponse results = db.getFormResult(user, formID);
        return results;
    }

    public List<FormResponse> viewResultsOfAForm(String formID) {
        Database db = new DatabaseImpl();
        List<FormResponse> listResults = db.getAllFormResponse(formID);
        return listResults;
    }

    // User-specific Functions
    public boolean submitForm(FormResponse response) {
        Database db = new DatabaseImpl();
        db.submitResponse(response);
        return true;
    }

    public FormResponse initFormResponse(String userName) {
        FormResponse responses = new FormResponse(userName);
        return responses;
    }

    // User-admin specific functions
    public FormFormat viewForm(String formID) {
        Database db = new DatabaseImpl();
        FormFormat form = db.getForm(formID);
        return form;
    }
}
