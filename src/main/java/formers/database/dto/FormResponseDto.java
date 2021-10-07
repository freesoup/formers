package formers.database.dto;

public class FormResponseDto {
    private String formID;
    private String formRecipient;
    private String stringJson;

    public FormResponseDto() {

    }

    public FormResponseDto(String id, String formRecipient, String json) {
        this.formID = id;
        this.formRecipient = formRecipient;
        this.stringJson = json;
    }

    public String getFormID() {
        return formID;
    }

    public void setFormID(final String formID) {
        this.formID = formID;
    }

    public String getStringJson() {
        return stringJson;
    }

    public void setStringJson(final String stringJson) {
        this.stringJson = stringJson;
    }

    public String getFormRecipient() {
        return formRecipient;
    }

    public void setFormRecipient(final String formRecipient) {
        this.formRecipient = formRecipient;
    }
}
