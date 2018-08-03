package formers.core.form.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateParser {
    public static Date ParseHTMLStringToDate(String html) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return formatter.parse(html);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            return null;
        }
    }

    public static String ParseDateToHTMLString(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        return formatter.format(date);
    }
}
