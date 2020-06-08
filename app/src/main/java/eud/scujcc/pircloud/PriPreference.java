package eud.scujcc.pircloud;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

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

    public void saveConfigure(Configure configure) {
        Log.d("TAG", "saveConfigure: " + configure.toString());
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(UserLab.USER_TOKEN, configure.getToken());
        editor.putString(File.INTERNALENDPOINT, configure.getInternalendpoint());
        editor.putString(File.BUKCKETNAME, configure.getBucketName());
        editor.putString(File.ACCESSKEYID, configure.getAccessKeyId());
        editor.putString(File.ACCESSKEYIDSECRET, configure.getAccessKeySecret());
        editor.apply();
    }

    public String currentUser(String username) {
        return preferences.getString(UserLab.USER_CURRENT, "");
    }

    public Configure getConfigure() {
        Configure configure = new Configure();
        configure.setInternalendpoint(preferences.getString(File.INTERNALENDPOINT, ""));
        configure.setBucketName(preferences.getString(File.BUKCKETNAME, ""));
        configure.setAccessKeyId(preferences.getString(File.ACCESSKEYID, ""));
        configure.setAccessKeySecret(preferences.getString(File.ACCESSKEYIDSECRET, ""));
        return configure;
    }

    public void clearConfigure() {
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear();
        editor.apply();
    }

    public String currentToken() {
        return preferences.getString(UserLab.USER_TOKEN, "");
    }
}
