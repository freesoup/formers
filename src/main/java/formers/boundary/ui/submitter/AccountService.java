package formers.boundary.ui.submitter;

public interface AccountService {
    public boolean createAccount(String user, String password);

    public boolean verifyLogIn(String user, String password);
}
