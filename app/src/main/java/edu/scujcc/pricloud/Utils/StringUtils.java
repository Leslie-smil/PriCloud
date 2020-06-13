package edu.scujcc.pricloud.Utils;

import java.text.DecimalFormat;

public class StringUtils {
    public static String formatDouble4(double d) {
        DecimalFormat df = new DecimalFormat("#.00");
        return df.format(d);
    }

}
