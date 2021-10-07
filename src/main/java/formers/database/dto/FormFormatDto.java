package formers.database.dto;

public class FormFormatDto {
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

    public void setFormID(final String id) {
        this.formID = id;
    }

    public String getFormOwner() {
        return formOwner;
    }

    public void setFormOwner(final String formOwner){
        this.formOwner = formOwner;
    }

    public String getFormFormatJson() {
        return formFormatJson;
    }

    public void setFormFormatJson(final String formFormatJson) {
        this.formFormatJson = formFormatJson;
    }
}
