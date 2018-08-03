package formers.core.authentication;

import formers.core.database.Database;
import formers.core.exception.DatabaseException;
import formers.database.impl.DatabaseImpl;

public class AuthenticatorImpl implements Authenticator {
    private Database db;

    public AuthenticatorImpl(Database db) {
        this.db = db;
    }

    @Override
    public boolean authenticate(String user, String password) throws DatabaseException {
        Database db = new DatabaseImpl();

        String incomingHashPass = String.valueOf(password.hashCode());

        String hashedPass = db.getPass(user);

        if (hashedPass == null) {
            return false;
        } else {
            return incomingHashPass.equals(hashedPass);
        }
    }

    @Override
    public Authorization getAuthorityLevel(String user) {
        Database db = new DatabaseImpl();

        Authorization authority = db.getAuthority(user);

        return authority;
    }
}
