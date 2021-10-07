package formers.database.dto;

import formers.core.authentication.Authorization;

public class AccountDto {
    String user;
    String hashedPass;
    Authorization authority;

    public AccountDto() {
    }

    public AccountDto(String user, String password, Authorization authority) {
        this.user = user;
        this.hashedPass = password;
        this.authority = authority;
    }

    public String getUser() {
        return user;
    }

    public void setUser(final String user) {
        this.user = user;
    }

    public String getHashedPass() {
        return hashedPass;
    }

    public void setHashedPass(final String hashedPass) {
        this.hashedPass = hashedPass;
    }

    public Authorization getAuthority() {
        return this.authority;
    }

    public void setAuthority(final Authorization authority) {
        this.authority = authority;
    }
}