package formers.boundary.ui.submitter;

import java.util.List;
import java.util.Map;

import formers.core.authentication.Authorization;
import formers.core.exception.DatabaseException;
import formers.core.exception.InsufficientAuthorityException;
import formers.core.form.utils.FormFormat;
import formers.core.form.utils.FormID;
import formers.core.form.utils.FormResponse;
import formers.core.form.utils.FormType;
import formers.core.form.utils.Option;
import formers.core.form.utils.Question;
import formers.core.form.utils.Response;
import formers.core.users.Player;

public class FormersSubmitterImpl implements FormersSubmitter {

    @Override
    public FormFormat submitNewForm(Map<String, String[]> map, String userName, Authorization authority)
            throws InsufficientAuthorityException {
        Player player = new Player();
        FormFormat newForm = player.initForm(userName, authority);

        String[] questions = map.get("question");
        String[] questionsType = map.get("type");
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
                question = new Question(questions[i], FormType.TEXT, FormID.generateFormID());
                break;

            case "checkbox":
                question = new Question(questions[i], FormType.CHECKBOX, FormID.generateFormID());
                String[] optionFields = map.get("optionfield-" + i);
                if (optionFields != null) {
                    for (int j = 0; j < optionFields.length; j++) {
                        question.addOption(new Option(optionFields[j], false));
                    }
                }
                break;
            case "radio":
                question = new Question(questions[i], FormType.RADIO, FormID.generateFormID());
                String[] optionFields1 = map.get("optionfield-" + i);
                if (optionFields1 != null) {
                    for (int j = 0; j < optionFields1.length; j++) {
                        question.addOption(new Option(optionFields1[j], false));
                    }
                }
                break;
            case "textarea":
                question = new Question(questions[i], FormType.TEXTAREA, FormID.generateFormID());
                break;
            default:
                return null;
            }

            newForm.addQuestion(question);
        }

        player.submitFormFormat(newForm, authority);

        return newForm;
    }

    @Override
    public FormResponse submitNewResponse(Map<String, String[]> map, String formID, String userName,
            Authorization authority) throws InsufficientAuthorityException, DatabaseException {
        Player player = new Player();
        FormResponse responses = player.initFormResponse(userName, authority);
        responses.setFormID(formID);

        FormFormat correspondingFormat = player.viewForm(formID);

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

        player.submitForm(responses, authority);

        return responses;
    }

}
