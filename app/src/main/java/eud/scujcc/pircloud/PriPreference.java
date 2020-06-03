package eud.scujcc.pircloud;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * 处理Share Preference中保存的数据
 *
 * @author FSMG
 */

public class PriPreference {
    private static PriPreference INSTANCE = null;
    private SharedPreferences preferences = null;
    private Context context = null;

    private PriPreference() {
    }

    public static PriPreference getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new PriPreference();
        }
        return INSTANCE;
    }

    public void setup(Context ctx) {
        context = ctx;
        preferences = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public void saveUser(String username, String token) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(UserLab.USER_CURRENT, username);
        editor.putString(UserLab.USER_TOKEN, token);
        editor.apply();
    }

    public String currentUser(String username) {
        return preferences.getString(UserLab.USER_CURRENT, "");
    }


    public String currentToken() {
        return preferences.getString(UserLab.USER_TOKEN, "");
    }
}
