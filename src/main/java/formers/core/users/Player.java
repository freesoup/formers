package formers.core.users;

import java.util.List;

import formers.core.authentication.Authorization;
import formers.core.database.Database;
import formers.core.exception.DatabaseException;
import formers.core.exception.InsufficientAuthorityException;
import formers.core.form.utils.FormFormat;
import formers.core.form.utils.FormID;
import formers.core.form.utils.FormResponse;
import formers.database.impl.DatabaseImpl;

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
    public FormFormat initForm(String user, Authorization authority) throws InsufficientAuthorityException {
        if (authority == Authorization.ADMIN) {
            FormFormat form = new FormFormat();
            form.addAdmin(user);
            form.addID(FormID.generateFormID());

            return form;
        } else {
            throw new InsufficientAuthorityException("Please sign up to use this function");
        }
    }

    public boolean submitFormFormat(FormFormat form, Authorization authority) throws InsufficientAuthorityException {
        if (authority == Authorization.ADMIN) {
            Database db = new DatabaseImpl();
            db.submitNewForm(form);
            return true;
        } else {
            throw new InsufficientAuthorityException("Please sign up to use this function");
        }
    }

    public List<FormFormat> viewAllForm(String user, Authorization authority) throws InsufficientAuthorityException {
        if (authority == Authorization.ADMIN) {
            Database db = new DatabaseImpl();
            List<FormFormat> formatList = db.getAllFormFormat(user);
            return formatList;
        } else {
            throw new InsufficientAuthorityException("Please sign up to use this function");
        }
    }

    // TODO: UNUSED KEPT FOR FUTURE EXTENSIONS
    public FormResponse viewResult(String formID, String user, Authorization authority)
            throws InsufficientAuthorityException {
        if (authority == Authorization.ADMIN) {
            Database db = new DatabaseImpl();
            FormResponse results = db.getFormResult(user, formID);
            return results;
        } else {
            throw new InsufficientAuthorityException("Please sign up to use this function");
        }
    }

    public List<FormResponse> viewResultsOfAForm(String formID, Authorization authority)
            throws InsufficientAuthorityException {
        if (authority == Authorization.ADMIN) {
            Database db = new DatabaseImpl();
            List<FormResponse> listResults = db.getAllFormResponse(formID);
            return listResults;
        } else {
            throw new InsufficientAuthorityException("Please sign up to use this function");
        }
    }

    // User-specific Functions
    // TODO: CHANGE the IF CLAUSE WHEN THERE IS SEPERATION BETWEEN USER AND STRANGER.
    public boolean submitForm(FormResponse response, Authorization authority) throws InsufficientAuthorityException {
        if (authority == Authorization.ADMIN
                || authority == Authorization.USER
                || authority == Authorization.STRANGER) {
            Database db = new DatabaseImpl();
            db.submitResponse(response);
            return true;
        } else {
            throw new InsufficientAuthorityException("Please sign up to use this function");
        }
    }

    // TODO: CHANGE the IF CLAUSE WHEN THERE IS SEPERATION BETWEEN USER AND STRANGER.
    public FormResponse initFormResponse(String userName, Authorization authority)
            throws InsufficientAuthorityException {
        if (authority == Authorization.ADMIN
                || authority == Authorization.USER
                || authority == Authorization.STRANGER) {
            FormResponse responses = new FormResponse(userName);
            return responses;
        } else {
            throw new InsufficientAuthorityException("Please sign up to use this function");
        }
    }

    // User-admin specific functions
    public FormFormat viewForm(String formID) throws DatabaseException {
        Database db = new DatabaseImpl();
        FormFormat form = db.getForm(formID);
        return form;
    }

    public void deleteAllTracesOf(String formIdToBeDeleted, String user, Authorization authority)
            throws DatabaseException, InsufficientAuthorityException {
        if (authority == Authorization.ADMIN) {
            Database db = new DatabaseImpl();
            db.deleteAllTracesOf(formIdToBeDeleted, user);
        } else {
            throw new InsufficientAuthorityException("Please sign up to use this function");
        }
    }
}
