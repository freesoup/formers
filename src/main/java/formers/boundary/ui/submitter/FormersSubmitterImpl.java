package formers.boundary.ui.submitter;

import java.util.Map;

import formers.core.form.utils.FormFormat;
import formers.core.form.utils.FormType;
import formers.core.form.utils.Option;
import formers.core.form.utils.Question;
import formers.core.users.AdminCore;

public class FormersSubmitterImpl implements FormersSubmitter {

    @Override
    public FormFormat submitNewForm(Map<String, String[]> map) {
        AdminCore admin = new AdminCore();
        FormFormat newForm = admin.initForm();

        String[] questions = map.get("question");
        String[] questionsType = map.get("type");
        String[] questionsParamName = map.get("name");
        newForm.addTitle(map.get("title")[0]);
        newForm.addPreamble(map.get("preamble")[0]);
        for (int i = 0; i < questions.length; i++) {
            Question question;
            switch (questionsType[i]) {
            case "text":
                question = new Question(questions[i], FormType.TEXT, questionsParamName[i]);
                break;

            case "checkbox":
                question = new Question(questions[i], FormType.CHECKBOX, questionsParamName[i]);
                String[] optionFields = map.get("optionfield-" + i);
                for (int j = 0; j < optionFields.length; j++) {
                    question.addOption(new Option(optionFields[j], false));
                }
                break;
            case "radio":
                question = new Question(questions[i], FormType.RADIO, questionsParamName[i]);
                String[] optionFields1 = map.get("optionfield-" + i);
                for (int j = 0; j < optionFields1.length; j++) {
                    question.addOption(new Option(optionFields1[j], false));
                }
                break;
            case "textarea":
                question = new Question(questions[i], FormType.TEXTAREA, questionsParamName[i]);
                break;
            default:
                return null;
            }

            newForm.addQuestion(question);
        }

        admin.submitFormFormat(newForm);

        return newForm;
    }

}
