package formers.core.database.dto;

import com.worksap.company.dto.annotation.Entity;
import com.worksap.company.dto.annotation.Key;

@Entity
public class FormResponseDto {
    @Key(order = 0)
    private String formId;
    @Key(order = 1)
    private String formRecipient;
    private String stringJson;

    public FormResponseDto() {

    }

    public FormResponseDto(String id, String formRecipient, String json) {
        this.formId = id;
        this.formRecipient = formRecipient;
        this.stringJson = json;
    }

    private String getFormID() {
        return formId;
    }

    public String getFormResponse() {
        return stringJson;
    }

    public String getRecipient() {
        return formRecipient;
    }
}
