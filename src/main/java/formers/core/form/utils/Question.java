package formers.core.form.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents the format of a single question in a form
 * 
 * @author jackietan
 *
 */
public class Question {
    private String question;
    private FormType type;
    private String paramName;
    private String placeholder;
    private List<Option> options;

    public Question(String question, FormType type, String name) {
        this.question = question;
        this.type = type;
        this.paramName = name;
        options = new ArrayList<Option>();
    }

    public void setPlaceholder(String info) {
        this.placeholder = info;
    }

    public void addOption(Option option) {
        options.add(option);
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

    public List<Option> getOptions() {
        return options;
    }
}
