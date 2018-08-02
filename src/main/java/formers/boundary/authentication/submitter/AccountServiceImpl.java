package formers.boundary.authentication.submitter;

import formers.core.authentication.Authenticator;
import formers.core.authentication.AuthenticatorImpl;
import formers.core.authentication.Authorization;
import formers.core.exception.DatabaseException;
import formers.core.users.AccountControl;

public class AccountServiceImpl implements AccountService {

    @Override
    public boolean createAccount(String user, String password) {
        AccountControl controller = new AccountControl();

        return controller.createAccount(user, password);
    }

    @Override
    public boolean verifyLogIn(String user, String password) throws DatabaseException {
        Authenticator controller = new AuthenticatorImpl();

        return controller.authenticate(user, password);
    }

    @Override
    public Authorization getAuthority(String user) {
        Authenticator controller = new AuthenticatorImpl();

        return controller.getAuthorityLevel(user);
    }

}
