package formers.core.users;

import java.util.List;

import formers.core.object.Form;
import formers.core.object.FormID;
import formers.core.object.Input;

public class AdminCore {
    String accountName;

    public Form initForm() {
        Form form = new Form();
        form.addAdmin(accountName);
        form.addID(FormID.generateFormID());

        return form;
    }

    public void addField(Form form, List<Input> inputList) {
        for (Input field : inputList) {
            form.addInput(field);
        }
    }

    public void viewResult(String ID) {

    }

}
