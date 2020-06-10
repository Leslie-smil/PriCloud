package eud.scujcc.pircloud;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.textfield.TextInputLayout;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Objects;


/**
 * @author FSMG
 */
public class RegisterActivity extends AppCompatActivity {
    private final static String TAG = "DD1";
    @SuppressLint("HandlerLeak")
    private final Handler handler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what) {
                case UserLab.USER_REGISTER_SUCCESS:
                    toMainActivity();
                    break;
                case UserLab.USER_REGISTER_FAIL:
                    Toast.makeText(RegisterActivity.this, "注册失败！", Toast.LENGTH_LONG).show();
                    break;
            }
        }
    };
    long b;
    private UserLab lab = UserLab.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        TextInputLayout username = findViewById(R.id.r_username);
        TextInputLayout password1 = findViewById(R.id.r_password);
        TextInputLayout password2 = findViewById(R.id.r_password2);
        //获取id对应的内容
        TextInputLayout phone = findViewById(R.id.r_phone);
        Button register = findViewById(R.id.r_register);


        //点击注册
        register.setOnClickListener(v -> {
            User u = new User();
            String s_gender;
            if (Objects.requireNonNull(username.getEditText()).getText().toString().length() > 0) {
                u.setUsername(Objects.requireNonNull(username.getEditText()).getText().toString());
                if (Objects.requireNonNull(password1.getEditText()).getText().toString().length() > 0 && Objects.requireNonNull(password2.getEditText()).getText().toString().length() > 0) {
                    //判断2个密码是否相等
                    if (Objects.requireNonNull(password2.getEditText()).getText().toString().equals(Objects.requireNonNull(password1.getEditText()).getText().toString())) {
                        u.setPassword(Objects.requireNonNull(password2.getEditText()).getText().toString());
                        Log.d(TAG, "onCreate: b" + b);
                        //LocalDateTime就是好用
                        u.setPhone(Objects.requireNonNull(phone.getEditText()).getText().toString());
                        Log.d(TAG, "onCreate: User" + u);
                        lab.register(u, handler);
                    } else {
                        Toast.makeText(RegisterActivity.this, "密码不一致！", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(RegisterActivity.this, "请输入密码！", Toast.LENGTH_LONG).show();
                }
            } else {
                Toast.makeText(RegisterActivity.this, "请输入用户名！", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void toMainActivity() {
        if (ClickUtil.isFastClick()) {  //过滤多次点击
            Toast.makeText(RegisterActivity.this, "注册成功！", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
            Toast.makeText(RegisterActivity.this, "欢迎来到PriCloud！", Toast.LENGTH_LONG).show();
            startActivity(intent);
        }
    }

}