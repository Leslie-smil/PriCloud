package eud.scujcc.pircloud.utils;

import java.text.DecimalFormat;

public class StringUtils {
    public static String formatDouble4(double d) {
        DecimalFormat df = new DecimalFormat("#.00");
        return df.format(d);
    }

}
