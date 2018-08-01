package formers.core.database.dto;

import com.worksap.company.dto.annotation.Entity;
import com.worksap.company.dto.annotation.Key;

import formers.core.authentication.Authorization;

@Entity
public class AccountDto {
    @Key
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

    public String getHashedPass() {
        return hashedPass;
    }

    public Authorization getAuthority() {
        return this.authority;
    }
}
