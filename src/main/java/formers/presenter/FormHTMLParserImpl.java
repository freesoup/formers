package formers.presenter;

import java.util.List;

import formers.core.form.utils.FormFormat;
import formers.core.form.utils.Input;

public class FormHTMLParserImpl implements FormHTMLParser {
    @Override
    public String parseFormFormatToHTML(FormFormat form) {
        List<Input> inputs = form.getFields();
        StringBuilder output = new StringBuilder();

        output.append("<h2>" + form.getPreamble() + "</h2>");
        output.append("<form action='formsubmit' method='post'>");

        for (Input input : inputs) {
            StringBuilder formHTML = new StringBuilder();
            switch (input.getType()) {
            case FIELDSTART:
                formHTML.append("<fieldset>");
                formHTML.append("<legend>" + input.getQuestion() + "</legend>");
                break;
            case FIELDEND:
                formHTML.append("</fieldset>");
                break;
            case TEXT:
                formHTML.append(input.getQuestion() + "<br>");
                formHTML.append("<input type='text' name='" + input.getParam() + "'><br>");
                break;
            case CHECKBOX:
            case RADIOBOX:
                String question = input.getQuestion();
                formHTML.append(
                        "<input type='radio' name='" + input.getParam() + "' value='" + question.toLowerCase() + "' ");
                if (input.getChecked()) {
                    formHTML.append("checked");
                }
                formHTML.append("> " + question + "<br>");
                break;
            case TEXTAREA:
                // TODO:
            case HIDDEN:
                // TODO:
            default:
                // TODO:
            }
            output.append(formHTML.toString());
        }

        output.append("</form><input type='submit' value='Submit Form'>  ");
        output.append("<input type='reset' value='Reset Form Fields'>");
        return output.toString();
    }

    @Override
    public FormFormat parseHTMLtoFormFormat(List<String> listInputHtml) {
        // TODO Auto-generated method stub
        return null;
    }

}
