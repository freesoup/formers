package formers.boundary.ui.presenter;

import java.util.List;

import formers.core.form.utils.FormFormat;

public interface FormHTMLParser {

    String parseFormFormatToHTML(FormFormat form);

    String parseFormFormatPreview(List<FormFormat> listForm);

}
