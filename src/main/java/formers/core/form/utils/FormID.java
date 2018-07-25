package formers.core.form.utils;

/**
 * Class to create a unique Form Identifier ID.
 * 
 * @author jackietan
 *
 */
public class FormID {
    public static String generateFormID() {
        // TODO:logic
        return Long.toHexString(Double.doubleToLongBits(Math.random()));
    }
}
