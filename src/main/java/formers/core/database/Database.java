package formers.core.database;

import java.util.List;

import formers.core.form.utils.FormFormat;
import formers.core.form.utils.FormResponse;
import formers.core.form.utils.FormResult;

public interface Database {
    public void submitResponse(FormResponse responses);

    public void submitNewForm(FormFormat form);

    public List<String> getAllFormId();

    public FormFormat getForm(String iD);

    public FormResult getFormResult(String iD);
}
