package edu.scujcc.pricloud;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import edu.scujcc.pricloud.Lab.UserLab;
import edu.scujcc.pricloud.Model.Configure;
import edu.scujcc.pricloud.Model.MyOSSFile;
import edu.scujcc.pricloud.Model.User;

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
        editor.putString(UserLab.USER_CURRENT, configure.getUser().getUsername());
        editor.putString(UserLab.USER_TOKEN, configure.getToken());
        editor.putString(MyOSSFile.INTERNALENDPOINT, configure.getInternalendpoint());
        editor.putString(MyOSSFile.BUKCKETNAME, configure.getBucketName());
        editor.putString(MyOSSFile.ACCESSKEYID, configure.getAccessKeyId());
        editor.putString(MyOSSFile.ACCESSKEYIDSECRET, configure.getAccessKeySecret());
        editor.apply();
    }

    public String currentUser(String username) {
        return preferences.getString(UserLab.USER_CURRENT, "");
    }

    public Configure getConfigure() {
        Configure configure = new Configure();
        User user = new User();
        user.setUsername(preferences.getString(UserLab.USER_CURRENT, ""));
        configure.setUser(user);
        configure.setInternalendpoint(preferences.getString(MyOSSFile.INTERNALENDPOINT, ""));
        configure.setBucketName(preferences.getString(MyOSSFile.BUKCKETNAME, ""));
        configure.setAccessKeyId(preferences.getString(MyOSSFile.ACCESSKEYID, ""));
        configure.setAccessKeySecret(preferences.getString(MyOSSFile.ACCESSKEYIDSECRET, ""));
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
