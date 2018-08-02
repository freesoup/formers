package formers.database.dto;

import com.worksap.company.dto.annotation.Entity;
import com.worksap.company.dto.annotation.Key;

@Entity
public class FormFormatDto {
    @Key
    private String formID;
    private String formOwner;
    private String formFormatJson;

    public FormFormatDto() {
    }

    public FormFormatDto(String id, String owner, String json) {
        this.formID = id;
        this.formOwner = owner;
        this.formFormatJson = json;
    }

    public String getFormID() {
        return formID;
    }

    public String formOwner() {
        return formOwner;
    }

    public String getFormFormat() {
        return formFormatJson;
    }
}
