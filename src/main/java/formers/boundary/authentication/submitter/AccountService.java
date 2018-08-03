package formers.boundary.authentication.submitter;

import formers.core.authentication.Authorization;
import formers.core.exception.DatabaseException;

public interface AccountService {
    public boolean createAccount(String user, String password);

    public boolean verifyLogIn(String user, String password) throws DatabaseException;

    public Authorization getAuthority(String user);
}
