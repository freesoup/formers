package formers.boundary.ui.presenter;

import java.util.Date;
import java.util.List;

import formers.core.form.utils.FormFormat;
import formers.core.form.utils.FormResponse;
import formers.core.form.utils.FormType;
import formers.core.form.utils.Option;
import formers.core.form.utils.Question;
import formers.core.users.AdminCore;
import formers.core.users.UserCore;

public class FormersPresenterImpl implements FormersPresenter {
    public String viewForm(String requestID) {
        UserCore user = new UserCore();
        FormFormat newForm = user.viewForm(requestID);

        FormHTMLParser parser = new FormHTMLParserImpl();
        String form = parser.parseFormFormatToHTML(newForm);

        return form;
    }

    @Override
    public String viewForm(FormFormat submittedForm) {
        FormHTMLParser parser = new FormHTMLParserImpl();
        String form = parser.parseFormFormatToHTML(submittedForm);

        return form;
    }

    @Override
    public String viewForms(String userName) {
        AdminCore admin = new AdminCore();
        List<FormFormat> listForm = admin.viewAllForm(userName);

        FormHTMLParser parser = new FormHTMLParserImpl();
        return parser.parseFormFormatPreview(listForm);
    }

    @Override
    public String viewResults(String formID) {
        // TODO:adasd
        AdminCore admin = new AdminCore();
        List<FormResponse> listResults = admin.viewResultsOfAForm(formID);
        return null;
    }

    @Override
    public Date getExpiry(String requestID) {
        UserCore user = new UserCore();
        FormFormat form = user.viewForm(requestID);
        
        return form.getDateExpiryinDate();
    }
}
