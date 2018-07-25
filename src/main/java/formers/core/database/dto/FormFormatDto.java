package formers.core.database.dto;

import com.worksap.company.dto.annotation.Entity;
import com.worksap.company.dto.annotation.Key;

@Entity
public class FormFormatDto {
    @Key
    private String formID;
    private String formFormatJson;

    public FormFormatDto(String id, String json) {
        this.formID = id;
        this.formFormatJson = json;
    }

    public String getFormID() {
        return formID;
    }

    public String getFormFormat() {
        return formFormatJson;
    }
}
