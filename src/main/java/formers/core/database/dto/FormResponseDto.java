package formers.core.database.dto;

import com.worksap.company.dto.annotation.Entity;
import com.worksap.company.dto.annotation.Key;

@Entity
public class FormResponseDto {
    @Key
    private String formId;
    private String stringJson;

    private FormResponseDto(String id, String json) {
        this.formId = id;
        this.stringJson = json;
    }

    private String getFormID() {
        return formId;
    }

    private String getFormResponse() {
        return stringJson;
    }
}
