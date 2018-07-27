package formers.boundary.ui.presenter;

import java.util.List;

import formers.core.form.utils.FormFormat;
import formers.core.form.utils.Option;
import formers.core.form.utils.Question;

public class FormHTMLParserImpl implements FormHTMLParser {
    @Override
    public String parseFormFormatToHTML(FormFormat form) {
        List<Question> questions = form.getQuestions();
        StringBuilder output = new StringBuilder();

        output.append("<h1>" + form.getTitle() + "</h1>");
        output.append("<h2>" + form.getPreamble() + "</h2>");
        output.append("<p>From is valid during this period " + form.getDateCreatedinString());
        output.append(" to " + form.getDateExpiryinString());
        output.append("<br>");

        for (Question question : questions) {
            StringBuilder formHTML = new StringBuilder();
            formHTML.append(question.getQuestion() + "<br>");

            switch (question.getType()) {
            case TEXT:
                formHTML.append("<input type='text' name='" + question.getParam() + "'>");
                break;
            case CHECKBOX:
                for (Option option : question.getOptions()) {
                    formHTML.append("<input type='checkbox' name='" + question.getParam() + "'");
                    formHTML.append("value='" + option.getValue().toLowerCase() + "'");
                    if (option.isChecked()) {
                        formHTML.append("checked");
                    }
                    formHTML.append(">" + option.getValue());
                    formHTML.append("<br>");
                }
                break;
            case RADIO:
                for (Option option : question.getOptions()) {
                    formHTML.append("<input type='radio' name='" + question.getParam() + "'");
                    formHTML.append("value='" + option.getValue().toLowerCase() + "'");
                    if (option.isChecked()) {
                        formHTML.append("checked");
                    }
                    formHTML.append(">" + option.getValue());
                    formHTML.append("<br>");
                }
                break;
            case TEXTAREA:
                formHTML.append("<textarea name='" + question.getParam() + "' cols='40' rows='5'></textarea>");
                break;
            default:
                // TODO:
            }

            formHTML.append("<br>");
            output.append(formHTML.toString());
        }

        return output.toString();
    }

    @Override
    public String parseFormFormatPreview(List<FormFormat> listForm) {
        StringBuilder output = new StringBuilder();

        for (FormFormat form : listForm) {
            String formName = form.getTitle();
            String formId = form.getID();

            output.append("<form action='formresult' method='post'><input type='hidden' name='requestID' value='"
                    + formId
                    + "'>");
            output.append("<input type='submit' value='" + formName + " Result'>");
            output.append("</form><br>");
        }

        return output.toString();
    }

}
