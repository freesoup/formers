package formers.presenter;

import formers.core.form.utils.FormFormat;
import formers.core.form.utils.FormType;
import formers.core.form.utils.Input;
import formers.core.users.AdminCore;

public class FormersPresenterImpl implements FormersPresenter {
    public String viewForm() {
        AdminCore admin = new AdminCore();
        FormFormat newForm = generateSampleForm();

        FormHTMLParser parser = new FormHTMLParserImpl();
        String form = parser.parseFormFormatToHTML(newForm);

        return form;
    }

    private FormFormat generateSampleForm() {
        FormFormat newForm = new FormFormat();
        newForm.addPreamble("Rate your experience");
        newForm.addID("abc132");
        newForm.addAdmin("tester1");
        newForm.addFormFieldStart("Questions");
        newForm.addInput(new Input("How would you rate it from 1~10", FormType.TEXT, "q1"));
        newForm.addFormFieldEnd();
        newForm.addFormFieldStart("What did you like most about the experience");
        newForm.addInput(new Input("The service was good", FormType.RADIOBOX, "experience"));
        newForm.addInput(new Input("The people were polite", FormType.RADIOBOX, "experience"));
        Input lastinput = new Input("NA", FormType.RADIOBOX, "experience");
        lastinput.setChecked(true);
        newForm.addInput(lastinput);
        newForm.addFormFieldEnd();
        return newForm;
    }
}
