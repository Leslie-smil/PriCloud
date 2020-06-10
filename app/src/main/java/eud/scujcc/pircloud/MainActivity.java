package eud.scujcc.pircloud;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.hz.android.easyadapter.EasyAdapter;

public class MainActivity extends AppCompatActivity implements contentAdapter.ContentClickListener {
    private final static String TAG = "PirCloud";
    private RecyclerView contenRv;
    private contentAdapter contentAdapter;
    private FolderLab lab = FolderLab.getInstance();
    private PriPreference priPreference = PriPreference.getInstance();
    private BottomNavigationView bottomNavigationView;

    private Handler handler = new Handler() {
        //按快捷键Ctrl o
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what) {
                case FolderLab.MSG_FILES:
                    contentAdapter.notifyDataSetChanged();
                    break;
                case FolderLab.MSG_FAILURE:
                    failed();
                    break;
            }
        }
    };

    private void failed() {
        Toast.makeText(MainActivity.this, "没有Token，不能访问哦", Toast.LENGTH_LONG).show();
        Log.w("PirCloud", "无Token，禁止访问");
    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        contentAdapter = new contentAdapter(this, MainActivity.this);

        this.contenRv = findViewById(R.id.content_rv);
        this.contenRv.setAdapter(contentAdapter);
        this.contenRv.setLayoutManager(new LinearLayoutManager(this));
        //点击模式
        contentAdapter.setSelectMode(EasyAdapter.SelectMode.CLICK);
        //单选模式
        contentAdapter.setSelectMode(EasyAdapter.SelectMode.SINGLE_SELECT);
        //多选模式
        contentAdapter.setSelectMode(EasyAdapter.SelectMode.MULTI_SELECT);
        initView();
        lab.getData(handler);
    }
    private void initView(){
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.bringToFront();
        //导航栏的监听器
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Log.d(TAG, item.getItemId() + " item was selected-------------------");
                onTabItemSelected(item.getItemId());//调用跳转方法
                return true;
            }

        });
    }
    //跳转方法
    private void onTabItemSelected(int id){
        switch (id){
            case R.id.page_1:
                break;
            case R.id.page_2:
                Intent intent = new Intent(MainActivity.this, LoadActivity.class);
                startActivity(intent);
                break;
            case R.id.page_3:
                Intent intent2 = new Intent(MainActivity.this, PersonalActivity.class);
                startActivity(intent2);
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        //把主线程的handler传递给子线程使用
        lab.refresh(handler);
    }
    @Override
    protected void onDestroy(){
        super.onDestroy();
        //退出清理缓存
        // priPreference.saveUser(priPreference.currentUser(UserLab.USER_CURRENT),null);

    }

    @Override
    public void onContentClick(int position) {

    }
}
