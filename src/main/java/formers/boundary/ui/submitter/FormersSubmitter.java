package formers.boundary.ui.submitter;

import java.util.Map;

import formers.core.authentication.Authorization;
import formers.core.exception.InsufficientAuthorityException;
import formers.core.form.utils.FormFormat;
import formers.core.form.utils.FormResponse;

public interface FormersSubmitter {
    FormFormat submitNewForm(Map<String, String[]> map, String user, Authorization authority)
            throws InsufficientAuthorityException;

    FormResponse submitNewResponse(Map<String, String[]> map, String formID, String user, Authorization authority)
            throws InsufficientAuthorityException;
}
