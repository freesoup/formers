package formers.core.users;

import formers.core.database.Database;
import formers.database.impl.DatabaseImpl;

public class AccountControl {
    private Database db;

    public AccountControl(Database db) {
        this.db = db;
    }

    public boolean createAccount(String user, String password) {
        String hashedPass = String.valueOf(password.hashCode());
        Database db = new DatabaseImpl();

        return db.addNewAccount(user, hashedPass);
    }
}
