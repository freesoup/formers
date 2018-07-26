package formers.core.database;

import java.util.List;

import formers.core.form.utils.FormFormat;
import formers.core.form.utils.FormResponse;

public interface Database {
    public void submitResponse(FormResponse responses);

    public void submitNewForm(FormFormat form);

    public List<FormFormat> getAllFormFormat(String userName);

    public List<FormResponse> getAllFormResponse(String formID);

    public FormFormat getForm(String iD);

    public FormResponse getFormResult(String user, String formID);
}
