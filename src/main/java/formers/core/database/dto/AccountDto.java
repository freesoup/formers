package formers.core.database.dto;

import com.worksap.company.dto.annotation.Entity;
import com.worksap.company.dto.annotation.Key;

@Entity
public class AccountDto {
    @Key
    String user;
    String hashedPass;

    public AccountDto() {
    }

    public AccountDto(String user, String password) {
        this.user = user;
        this.hashedPass = password;
    }

    public String getUser() {
        return user;
    }

    public String getHashedPass() {
        return hashedPass;
    }
}
