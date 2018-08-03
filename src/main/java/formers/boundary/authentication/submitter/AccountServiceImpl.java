package formers.boundary.authentication.submitter;

import formers.core.authentication.Authenticator;
import formers.core.authentication.Authorization;
import formers.core.exception.DatabaseException;
import formers.core.users.AccountControl;

public class AccountServiceImpl implements AccountService {
    private AccountControl controller;
    private Authenticator authenticator;

    public AccountServiceImpl(AccountControl controller, Authenticator authenticator) {
        this.controller = controller;
        this.authenticator = authenticator;
    }

    @Override
    public boolean createAccount(String user, String password) {
        return controller.createAccount(user, password);
    }

    @Override
    public boolean verifyLogIn(String user, String password) throws DatabaseException {
        return authenticator.authenticate(user, password);
    }

    @Override
    public Authorization getAuthority(String user) {
        return authenticator.getAuthorityLevel(user);
    }

}
