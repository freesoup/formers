package formers.boundary.ui.presenter;

import formers.core.form.utils.FormFormat;

public interface FormersPresenter {
    String viewForm();

    String viewForm(FormFormat submittedForm);
}
