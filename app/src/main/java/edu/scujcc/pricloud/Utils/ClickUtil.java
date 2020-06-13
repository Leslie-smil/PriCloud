package edu.scujcc.pricloud.Utils;

/**
 * @author FSMG
 */
public class ClickUtil {
    private static final int MIN_CLICK_DELAY_TIME = 200;
    private static long lastClickTime;

    public static boolean isFastClick() {
        boolean flag = false;
        long curClickTime = System.currentTimeMillis();
        if ((curClickTime - lastClickTime) >= MIN_CLICK_DELAY_TIME) {
            flag = true;
        }
        lastClickTime = curClickTime;
        return flag;
    }
}
