package eud.scujcc.pircloud;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import java.io.IOException;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


/**
 * @author FSMG
 */
public class UserLab {
     static final int USER_LOGIN_FAIL = -1;
     static final int USER_LOGIN_SUCCESS = 1;
     static final int USER_REGISTER_SUCCESS = 1;
     static final int USER_REGISTER_FAIL = 0;
    static final String USER_CURRENT = "USER_CURRENT";
    static final String USER_TOKEN = "TOKEN";
    private static UserLab INSTANCE = null;
    private static String TAG = "DD1";

    private UserLab() {
    }

    //单例模式
 public    static UserLab getInstance() {
        if (null == INSTANCE) {
            INSTANCE = new UserLab();
        }
        return INSTANCE;
    }
public void   getSpInfo( Callback<Result<String>> callback)
{
    Retrofit retrofit = RetrofitClient.getInstance();
    UserApi api = retrofit.create(UserApi.class);
    Call<Result<String>> call = api.spInfo();
    call.enqueue(callback);
}
    //User登录
    void login(String username, String password, Handler handler) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        Retrofit retrofit = RetrofitClient.getInstance();
        UserApi api = retrofit.create(UserApi.class);
        Call<Result<Configure>> call = api.login(user);
        call.enqueue(new Callback<Result<Configure>>() {
            @Override
            public void onResponse(Call<Result<Configure>> call, Response<Result<Configure>> response) {
                Result<Configure> result = response.body();
                if (result != null) {

                    Log.e("=======test","login data:"+result.toString());
                    switch (result.getStatus()) {
                        case 1:
                            Log.d(TAG, "登录成功!");
                            Message msg1 = new Message();
                            msg1.what = USER_LOGIN_SUCCESS;
                            msg1.obj = result.getData();

                            Log.e("======test","login result.getData:"+ result.getData().toString());

                            handler.sendMessage(msg1);
                            break;
                        case -1:
                            Message msg_1 = new Message();
                            msg_1.what = USER_LOGIN_FAIL;
                            handler.sendMessage(msg_1);
                            break;
                        default:
                            Log.d(TAG, "多设备登录");
                            Message msg2 = new Message();
                            msg2.what = USER_LOGIN_SUCCESS;
                            msg2.obj = result.getData();
                            handler.sendMessage(msg2);
                    }
                }
            }

            @Override
            public void onFailure(Call<Result<Configure>> call, Throwable t) {
                Log.e(TAG, "登录失败", t);
            }
        });
    }

    //User注册
    void register(User user, Handler handler) {
        Retrofit retrofit = RetrofitClient.getInstance();
        UserApi api = retrofit.create(UserApi.class);
        Call<Result<User>> call = api.register(user);
        call.enqueue(new Callback<Result<User>>() {
            @Override
            public void onResponse( Call<Result<User>> call, Response<Result<User>> response) {
                Result result = response.body();
                Log.d(TAG, "onResponse: " + result);
                if (result != null) {
                    switch (result.getStatus()) {
                        case 1:
                            Message msg1 = new Message();
                            msg1.what = USER_REGISTER_SUCCESS;
                            handler.sendMessage(msg1);
                            break;
                        case -1:
                            Message msg0 = new Message();
                            msg0.what = USER_REGISTER_FAIL;
                            handler.sendMessage(msg0);
                            break;

                    }
                } else {
                    Log.d(TAG, "onResponse:  空");
                }
            }

            @Override
            public void onFailure( Call<Result<User>> call,  Throwable t) {

            }
        });
    }


}
