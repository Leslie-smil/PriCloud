package edu.scujcc.pricloud.Activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import edu.scujcc.pricloud.PriPreference;
import edu.scujcc.pricloud.R;

public class PersonalActivity extends Activity implements View.OnClickListener {

    PriPreference priPreference = PriPreference.getInstance();

    ImageView head;
    Button btnManager, logout;
    LinearLayout btnRecycle;
    LinearLayout btnSetting;
    TextView username;

//    private BottomNavigationView bottomNavigationView;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal);

        initView();
        if (priPreference.getConfigure().getUser().getUsername() != null) {
            username.setText(priPreference.getConfigure().getUser().getUsername());
        }

    }

    void initView() {
        head = findViewById(R.id.head);
        btnManager = findViewById(R.id.btnManager);
        logout = findViewById(R.id.logout);
        btnRecycle = findViewById(R.id.btnRecycle);
        btnSetting = findViewById(R.id.btnSetting);
        username = findViewById(R.id.user_name);
//        bottomNavigationView = findViewById(R.id.bottom_navigation);
        head.setOnClickListener(this);
        btnManager.setOnClickListener(this);
        logout.setOnClickListener(this);
        btnRecycle.setOnClickListener(this);
        btnSetting.setOnClickListener(this);
//        bottomNavigationView.bringToFront();
//        //导航栏的监听器
//        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//                Log.d(TAG, item.getItemId() + " item was selected-------------------");
//                onTabItemSelected(item.getItemId());//调用跳转方法
//                return true;
//            }
//
//        });
//        bottomNavigationView.getMenu().getItem(2).setChecked(true);//设置默认选中item


    }

    //跳转方法
    private void onTabItemSelected(int id) {
        switch (id) {
            case R.id.page_1:
                Intent intent = new Intent(PersonalActivity.this, MyFilesActivity.class);
                startActivity(intent);
                break;
            case R.id.page_2:
                Intent intent2 = new Intent(PersonalActivity.this, LoadActivity.class);
                startActivity(intent2);
                break;
            case R.id.page_3:

                break;
        }
    }

    @Override
    public void onClick(View v) {
        int _id = v.getId();

        switch (_id) {
            case R.id.head:
                toPageActivity(UserInfoActivity.class);
                break;
            case R.id.btnManager:
                toPageActivity(SpManagerActivity.class);
                break;
            case R.id.logout:
                toPageActivity(LoginActivity.class);
                break;
            case R.id.btnRecycle:
                toPageActivity(RecycleBinActivity.class);
                break;
            case R.id.btnSetting:
                toPageActivity(UserSetActivity.class);

                break;

        }

    }


    private void toPageActivity(Class<?> page) {

        Intent intent = new Intent(this, page);

        startActivity(intent);
    }

}
