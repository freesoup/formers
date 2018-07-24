package formers.presenter;

import java.util.ArrayList;
import java.util.List;

import formers.core.form.utils.FormFormat;
import formers.core.form.utils.Input;

public class FormHTMLParserImpl implements FormHTMLParser {
    @Override
    public List<String> parseFormFormatToHTML(FormFormat form) {
        List<Input> inputs = form.getFields();
        List<String> output = new ArrayList<String>();
        for (Input input : inputs) {
            StringBuilder formHTML = new StringBuilder();
            switch (input.getType()) {
            case FIELDSTART:
                formHTML.append(input.getClass());
                formHTML.append("<fieldset>");
                formHTML.append("<legend>" + input.getQuestion() + "</legend>");
                break;
            case FIELDEND:
                formHTML.append("</fieldset>");
                break;
            case TEXT:
                formHTML.append(input.getQuestion() + "<br>");
                formHTML.append("<input type='text' name='" + input.getParam() + "'><br>");
            case CHECKBOX:
            case RADIOBOX:
            case TEXTAREA:
            case HIDDEN:
            default:
            }
            output.add(formHTML.toString());
        }
        return output;
    }

    @Override
    public FormFormat parseHTMLtoFormFormat(List<String> listInputHtml) {
        // TODO Auto-generated method stub
        return null;
    }

}
