package eud.scujcc.pircloud;

import com.squareup.moshi.FromJson;
import com.squareup.moshi.ToJson;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


/**
 * @author FSMG
 */
public class DateAdapter {
    private final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss",Locale.forLanguageTag("GMT+8:00"));

    @ToJson
    String toJson(Date dt) {
        return dateFormat.format(dt);
    }

    @FromJson
    Date fromJson(String jsonDt) throws ParseException {
        return dateFormat.parse(jsonDt);
    }
}

