package formers.core.form.utils;

public class Option {
    private String value;
    private boolean checked;

    public Option(String value, boolean checked) {
        this.value = value;
        this.checked = checked;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }
}
