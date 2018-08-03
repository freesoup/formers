package formers.boundary.ui.presenter;

import java.util.Date;

import formers.core.authentication.Authorization;
import formers.core.exception.DatabaseException;
import formers.core.exception.InsufficientAuthorityException;
import formers.core.form.utils.FormFormat;

public interface FormersPresenter {
    String viewForm(String requestID) throws DatabaseException;

    String viewForm(FormFormat submittedForm);

    String viewResults(String formID, Authorization authority) throws InsufficientAuthorityException;

    Date getExpiry(String requestID) throws DatabaseException;
}
