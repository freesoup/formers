package formers.core.users;

import formers.core.database.Database;
import formers.database.impl.DatabaseImpl;

public class AccountControl {
    public boolean createAccount(String user, String password) {
        String hashedPass = String.valueOf(password.hashCode());
        Database db = new DatabaseImpl();

        return db.addNewAccount(user, hashedPass);
    }
}
