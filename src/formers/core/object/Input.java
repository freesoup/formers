package formers.core.object;

public class Input {
    private String question;
    // TODO: Change to enum
    private String type;
    private String paramName;
    private String placeholder;
    private boolean checked;

    public Input(String question, String type, String name) {
        this.question = question;
        this.type = type;
        this.paramName = name;
    }

    public void setPlaceholder(String info) {
        this.placeholder = info;
    }

    public void setChecked(boolean enabled) {
        this.checked = enabled;
    }
}
