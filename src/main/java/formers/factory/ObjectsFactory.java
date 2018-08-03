package formers.factory;

import formers.boundary.authentication.submitter.AccountService;
import formers.boundary.authentication.submitter.AccountServiceImpl;
import formers.boundary.ui.presenter.FormersPresenter;
import formers.boundary.ui.presenter.FormersPresenterImpl;
import formers.boundary.ui.submitter.FormersSubmitter;
import formers.boundary.ui.submitter.FormersSubmitterImpl;
import formers.core.authentication.Authenticator;
import formers.core.authentication.AuthenticatorImpl;
import formers.core.database.Database;
import formers.core.users.AccountControl;
import formers.core.users.AdminCore;
import formers.core.users.Player;
import formers.core.users.UserCore;
import formers.database.impl.DatabaseImpl;

public class ObjectsFactory {

    public static Database getDatabase() {
        return new DatabaseImpl();
    }

    // Form-related Classes
    public static AdminCore getAdminCore() {
        return new AdminCore(getDatabase());
    }

    private static UserCore getUserCore() {
        return new UserCore(getDatabase());
    }

    public static Player getPlayer() {
        return new Player(getAdminCore(), getUserCore());
    }

    public static FormersPresenter getFormsPresenter() {
        return new FormersPresenterImpl(getPlayer());
    }

    public static FormersSubmitter getFormsSubmitter() {
        return new FormersSubmitterImpl(getPlayer());
    }

    // Account-related Classes
    private static Authenticator getAuthenticator() {
        return new AuthenticatorImpl(getDatabase());
    }

    private static AccountControl getAccountController() {
        return new AccountControl(getDatabase());
    }

    public static AccountService getAccountService() {
        return new AccountServiceImpl(getAccountController(), getAuthenticator());
    }
}
