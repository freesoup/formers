package formers.boundary.ui.submitter;

import java.util.List;
import java.util.Map;

import formers.core.form.utils.FormFormat;
import formers.core.form.utils.FormResponse;
import formers.core.form.utils.FormType;
import formers.core.form.utils.Option;
import formers.core.form.utils.Question;
import formers.core.form.utils.Response;
import formers.core.users.AdminCore;
import formers.core.users.UserCore;

public class FormersSubmitterImpl implements FormersSubmitter {

    @Override
    public FormFormat submitNewForm(Map<String, String[]> map, String userName) {
        AdminCore admin = new AdminCore();
        FormFormat newForm = admin.initForm(userName);

        String[] questions = map.get("question");
        String[] questionsType = map.get("type");
        String[] questionsParamName = map.get("name");
        newForm.addTitle(map.get("title")[0]);
        newForm.addPreamble(map.get("preamble")[0]);

        String dateCreated = map.get("createdate")[0];

        if (dateCreated != null && !dateCreated.isEmpty()) {
            newForm.setDateCreated(dateCreated);
        }

        newForm.setDateExpiry(map.get("expirydate")[0]);

        for (int i = 0; i < questions.length; i++) {
            Question question;
            switch (questionsType[i]) {
            case "text":
                question = new Question(questions[i], FormType.TEXT, questionsParamName[i]);
                break;

            case "checkbox":
                question = new Question(questions[i], FormType.CHECKBOX, questionsParamName[i]);
                String[] optionFields = map.get("optionfield-" + i);
                if (optionFields != null) {
                    for (int j = 0; j < optionFields.length; j++) {
                        question.addOption(new Option(optionFields[j], false));
                    }
                }
                break;
            case "radio":
                question = new Question(questions[i], FormType.RADIO, questionsParamName[i]);
                String[] optionFields1 = map.get("optionfield-" + i);
                if (optionFields1 != null) {
                    for (int j = 0; j < optionFields1.length; j++) {
                        question.addOption(new Option(optionFields1[j], false));
                    }
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

    @Override
    public FormResponse submitNewResponse(Map<String, String[]> map, String formID, String userName) {
        UserCore user = new UserCore();
        FormResponse responses = user.initFormResponse(userName);
        responses.setFormID(formID);

        FormFormat correspondingFormat = user.viewForm(formID);

        List<Question> questions = correspondingFormat.getQuestions();

        for (int i = 0; i < questions.size(); i++) {
            Question currQuestion = questions.get(i);

            String question = currQuestion.getQuestion();
            FormType type = currQuestion.getType();
            String param = currQuestion.getParam();
            Response response = new Response(question);

            switch (type) {
            case TEXT:
            case RADIO:
            case TEXTAREA:
                String answer = map.get(param)[0];
                response.addAnswer(answer);
                break;

            case CHECKBOX:
                String[] answerList = map.get(param);
                for (int j = 0; j < answerList.length; j++) {
                    response.addAnswer(answerList[j]);
                }
            }

            responses.addResponse(response);
        }

        user.submitForm(responses);

        return responses;
    }

}
