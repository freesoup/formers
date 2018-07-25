package formers.boundary.ui.submitter;

import java.util.Map;

import formers.core.form.utils.FormFormat;

public interface FormersSubmitter {
    FormFormat submitNewForm(Map<String, String[]> map);
}
