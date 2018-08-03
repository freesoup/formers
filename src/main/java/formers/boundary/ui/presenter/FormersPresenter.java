package formers.boundary.ui.presenter;

import java.util.Date;

import formers.core.exception.DatabaseException;
import formers.core.form.utils.FormFormat;

public interface FormersPresenter {
    String viewForm(String requestID) throws DatabaseException;

    String viewForm(FormFormat submittedForm);

    Date getExpiry(String requestID) throws DatabaseException;
}
