package formers.core.database.dto;

import com.worksap.company.dto.annotation.Entity;
import com.worksap.company.dto.annotation.Key;

@Entity
public class FormResponseDto {
    @Key
    private String formId;
    private String formRecipient;
    private String stringJson;

    private FormResponseDto(String id, String formRecipient, String json) {
        this.formId = id;
        this.formRecipient = formRecipient;
        this.stringJson = json;
    }

    private String getFormID() {
        return formId;
    }

    private String getFormResponse() {
        return stringJson;
    }

    private String getRecipient() {
        return formRecipient;
    }
}
