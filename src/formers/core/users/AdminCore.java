package formers.core.users;

import java.util.List;

import formers.core.object.FormFormat;
import formers.core.object.FormID;
import formers.core.object.Input;

/**
 * Provides access to administrative user functionalities.
 * 
 * @author jackietan
 *
 */
public class AdminCore {
    String accountName;

    public FormFormat initForm() {
        FormFormat form = new FormFormat();
        form.addAdmin(accountName);
        form.addID(FormID.generateFormID());

        return form;
    }

    public void addField(FormFormat form, List<Input> inputList) {
        for (Input field : inputList) {
            form.addInput(field);
        }
    }

    public void viewResult(String ID) {

    }
}
