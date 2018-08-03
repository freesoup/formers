package formers.core.form.utils;

import java.util.UUID;

/**
 * Class to create a unique Form Identifier ID.
 * 
 * @author jackietan
 *
 */
public class FormID {
    public static String generateFormID() {
        // TODO:logic
        return UUID.randomUUID().toString();
    }
}
