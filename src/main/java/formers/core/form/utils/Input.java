package formers.core.form.utils;

/**
 * Represents the format of a single question in a form
 * 
 * @author jackietan
 *
 */
public class Input {
    private String question;
    // TODO: Change to enum
    private FormType type;
    private String paramName;
    private String placeholder;
    private boolean checked;

    public Input(String question, FormType type, String name) {
        this.question = question;
        this.type = type;
        this.paramName = name;
    }

    public Input(String question, FormType type) {
        this.question = question;
        this.type = type;
    }

    public Input(FormType type) {
        this.type = type;
    }

    public void setPlaceholder(String info) {
        this.placeholder = info;
    }

    public void setChecked(boolean enabled) {
        this.checked = enabled;
    }

    public FormType getType() {
        return this.type;
    }

    public String getQuestion() {
        return this.question;
    }

    public String getParam() {
        return paramName;
    }

    public String getPlaceholder() {
        return placeholder;
    }

    public boolean getChecked() {
        return checked;
    }
}
