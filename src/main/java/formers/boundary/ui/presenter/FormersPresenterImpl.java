package formers.boundary.ui.presenter;

import formers.core.form.utils.FormFormat;
import formers.core.form.utils.FormType;
import formers.core.form.utils.Option;
import formers.core.form.utils.Question;
import formers.core.users.AdminCore;

public class FormersPresenterImpl implements FormersPresenter {
    public String viewForm(String requestID) {
        AdminCore admin = new AdminCore();
        FormFormat newForm = admin.viewForm(requestID);

        FormHTMLParser parser = new FormHTMLParserImpl();
        String form = parser.parseFormFormatToHTML(newForm);

        return form;
    }

    private FormFormat generateSampleForm() {
        FormFormat newForm = new FormFormat();
        newForm.addTitle("The Best Grocery Store Great Survey");
        newForm.addPreamble("Rate your experience");
        newForm.addID("abc132");
        newForm.addAdmin("tester1");

        Question q1 = new Question("How would you rate it from 1~10", FormType.TEXT, "q1");
        newForm.addQuestion(q1);

        Question q2 = new Question("What did you enjoy the most during your time here", FormType.RADIO, "q2");
        q2.addOption(new Option("The service was good", false));
        q2.addOption(new Option("The people were polite", false));
        q2.addOption(new Option("NA for me", true));
        newForm.addQuestion(q2);

        Question q3 = new Question("What product did u buy", FormType.CHECKBOX, "q3");
        q3.addOption(new Option("Milk", false));
        q3.addOption(new Option("Vegetables", false));
        q3.addOption(new Option("Spinach", false));
        q3.addOption(new Option("NA for me", true));
        newForm.addQuestion(q3);

        Question q4 = new Question("Do you have any other feedback", FormType.TEXTAREA, "q4");
        newForm.addQuestion(q4);
        return newForm;
    }

    @Override
    public String viewForm(FormFormat submittedForm) {
        FormHTMLParser parser = new FormHTMLParserImpl();
        String form = parser.parseFormFormatToHTML(submittedForm);

        return form;
    }
}
