package formers.core.authentication;

public interface Authenticator {
    public boolean authenticate(String user, String password);

    Authorization getAuthorityLevel(String user);
}
