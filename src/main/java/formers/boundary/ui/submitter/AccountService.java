package formers.boundary.ui.submitter;

import formers.core.authentication.Authorization;

public interface AccountService {
    public boolean createAccount(String user, String password);

    public boolean verifyLogIn(String user, String password);

    public Authorization getAuthority(String user);
}
