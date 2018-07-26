package formers.core.form.utils;

import java.util.ArrayList;
import java.util.List;

public class Response {
    String question;
    List<String> answers;

    public Response(String question) {
        this.question = question;
        answers = new ArrayList<String>();
    }

    public void addAnswer(String answer) {
        this.answers.add(answer);
    }

    public String getQuestion() {
        return this.getQuestion();
    }

    public List<String> getAnswers() {
        return this.answers;
    }
}
