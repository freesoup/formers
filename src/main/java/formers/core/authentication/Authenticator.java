package formers.core.authentication;

import formers.core.exception.DatabaseException;

public interface Authenticator {
    public boolean authenticate(String user, String password) throws DatabaseException;

    Authorization getAuthorityLevel(String user);
}
