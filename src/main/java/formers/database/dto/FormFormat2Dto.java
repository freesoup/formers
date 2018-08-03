package formers.database.dto;

import com.worksap.company.dto.annotation.Entity;
import com.worksap.company.dto.annotation.Key;

@Entity
public class FormFormat2Dto {
    @Key(order = 0)
    private String formOwner;
    @Key(order = 1)
    private String formID;
    private String formFormatJson;

    public FormFormat2Dto() {
    }

    public FormFormat2Dto(String id, String owner, String json) {
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
