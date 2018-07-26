package formers.boundary.ui.submitter;

import java.util.Map;

import formers.core.form.utils.FormFormat;
import formers.core.form.utils.FormResponse;

public interface FormersSubmitter {
    FormFormat submitNewForm(Map<String, String[]> map, String user);

    FormResponse submitNewResponse(Map<String, String[]> map, String formID, String user);
}
