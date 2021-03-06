package formers.presenter;

import org.junit.Before;
import org.junit.Test;

import formers.boundary.ui.presenter.FormHTMLParser;
import formers.boundary.ui.presenter.FormHTMLParserImpl;
import formers.core.form.utils.FormFormat;
import formers.core.form.utils.FormType;
import formers.core.form.utils.Question;

public class FormHTMLParserTest {
    FormHTMLParser form;

    @Before
    public void setUp() {
        form = new FormHTMLParserImpl();
    }

    @Test
    public void parserTest() {
        FormFormat newForm = new FormFormat();
        newForm.addPreamble("Rate your experience");
        newForm.addID("abc132");
        newForm.addAdmin("tester1");
        newForm.addQuestion(new Question("How would you rate it from 1~10", FormType.TEXT, "q1"));
        String htmlInputs = form.parseFormFormatToHTML(newForm);

        System.out.println(htmlInputs);
    }
}
