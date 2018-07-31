package formers.boundary.ui.presenter;

import java.util.Date;

import formers.core.form.utils.FormFormat;

public interface FormersPresenter {
    String viewForm(String requestID);

    String viewForm(FormFormat submittedForm);

    String viewForms(String userName);

    String viewResults(String formID);

    Date getExpiry(String requestID);
}
