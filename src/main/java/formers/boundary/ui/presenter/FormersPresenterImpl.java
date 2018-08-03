package formers.boundary.ui.presenter;

import java.util.Date;

import formers.core.exception.DatabaseException;
import formers.core.form.utils.FormFormat;
import formers.core.users.Player;

public class FormersPresenterImpl implements FormersPresenter {
    private Player player;

    public FormersPresenterImpl(Player player) {
        this.player = player;
    }

    public String viewForm(String requestID) throws DatabaseException {
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
    public Date getExpiry(String requestID) throws DatabaseException {
        FormFormat form = player.viewForm(requestID);

        return form.getDateExpiryinDate();
    }
}
