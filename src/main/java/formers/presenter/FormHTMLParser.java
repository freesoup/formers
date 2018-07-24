package formers.presenter;

import java.util.List;

import formers.core.form.utils.FormFormat;

public interface FormHTMLParser {

    String parseFormFormatToHTML(FormFormat form);

    FormFormat parseHTMLtoFormFormat(List<String> listInputHtml);

}
