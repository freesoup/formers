package formers.core.database;

import java.util.List;

import formers.core.authentication.Authorization;
import formers.core.form.utils.FormFormat;
import formers.core.form.utils.FormResponse;

public interface Database {
    public void submitResponse(FormResponse responses);

    public void submitNewForm(FormFormat form);

    public List<FormFormat> getAllFormFormat(String userName);

    public List<FormResponse> getAllFormResponse(String formID);

    public FormFormat getForm(String iD);

    public FormResponse getFormResult(String user, String formID);

    public String getPass(String user);

    public boolean addNewAccount(String user, String hashedPass);

    public Authorization getAuthority(String user);
}
