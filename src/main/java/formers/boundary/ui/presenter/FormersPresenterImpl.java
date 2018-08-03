package formers.boundary.ui.presenter;

import java.util.Date;
import java.util.List;

import formers.core.authentication.Authorization;
import formers.core.exception.DatabaseException;
import formers.core.exception.InsufficientAuthorityException;
import formers.core.form.utils.FormFormat;
import formers.core.form.utils.FormResponse;
import formers.core.users.Player;

public class FormersPresenterImpl implements FormersPresenter {
    public String viewForm(String requestID) throws DatabaseException {
        Player player = new Player();
        FormFormat newForm = player.viewForm(requestID);

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
    public String viewResults(String formID, Authorization authority) throws InsufficientAuthorityException {
        // TODO:adasd
        Player admin = new Player();
        List<FormResponse> listResults = admin.viewResultsOfAForm(formID, authority);
        return null;
    }

    @Override
    public Date getExpiry(String requestID) throws DatabaseException {
        Player user = new Player();
        FormFormat form = user.viewForm(requestID);

        return form.getDateExpiryinDate();
    }
}
