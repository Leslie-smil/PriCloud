package eud.scujcc.pircloud;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;


/**
 * @author FSMG
 */
public class LoginActivity extends AppCompatActivity {
    String t_username, t_password;
    private TextInputLayout username;
    private TextInputLayout password;
    private UserLab lab = UserLab.getInstance();
    private PriPreference priPreference = PriPreference.getInstance();
    @SuppressLint("HandlerLeak")
    private final Handler handler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what) {
                case UserLab.USER_LOGIN_SUCCESS:
                    Log.d("TAG", "handleMessage: " + msg.obj.toString());
                    loginSuccess(msg.obj);
                    break;
                case UserLab.USER_LOGIN_FAIL:
                    loginFail();
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        Button login = findViewById(R.id.login_button);
        Button register = findViewById(R.id.register_button);

        login.setOnClickListener(v -> {
            if(priPreference.currentToken().isEmpty()){
                t_username = Objects.requireNonNull(username.getEditText()).getText().toString();
                t_password = Objects.requireNonNull(password.getEditText()).getText().toString();
                if (t_username.length() > 0) {
                    if (t_password.length() > 0) {
                        lab.login(t_username, t_password, handler);
                    } else {
                        Toast.makeText(LoginActivity.this, "请输入密码！", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(LoginActivity.this, "请输入用户名！", Toast.LENGTH_LONG).show();
                }
            }else {
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
            }

        });
        register.setOnClickListener(v -> register());

        priPreference.setup(getApplicationContext());
    }

    private void loginSuccess(Object object) {
        if (ClickUtil.isFastClick()) {
            Toast.makeText(LoginActivity.this, "登录成功！", Toast.LENGTH_LONG).show();
            Configure configure = (Configure) object;
            priPreference.saveConfigure(configure);
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
        }
    } //跳转主页面

    private void register() {
        if (ClickUtil.isFastClick()) {
            Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
            startActivity(intent);
        }
    } //进入注册界面


    private void loginFail() {
        Toast.makeText(LoginActivity.this, "登录失败！", Toast.LENGTH_LONG).show();
    } //登录失败提示
}
