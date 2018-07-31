package formers.core.authentication;

import formers.core.database.Database;
import formers.core.database.DatabaseImpl;

public class AuthenticatorImpl implements Authenticator {

    @Override
    public boolean authenticate(String user, String password) {
        Database db = new DatabaseImpl();

        String incomingHashPass = String.valueOf(password.hashCode());

        String hashedPass = db.getPass(user);

        if (hashedPass == null) {
            return false;
        } else {
            return incomingHashPass.equals(hashedPass);
        }
    }
}
