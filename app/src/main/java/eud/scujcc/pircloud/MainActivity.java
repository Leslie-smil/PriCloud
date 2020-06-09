package eud.scujcc.pircloud;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;


public class MainActivity extends AppCompatActivity implements contentAdapter.ContentClickListener {
    private final static String TAG = "PirCloud";
    private RecyclerView contenRv;
    private contentAdapter contentAdapter;

    private FolderLab lab = FolderLab.getInstance();
    private PriPreference priPreference = PriPreference.getInstance();
    private BottomNavigationView bottomNavigationView;
    private Fragment[] mFragmensts;
//    LayoutInflater factory = LayoutInflater.from(MainActivity.this);
//    View layout = factory.inflate(R.layout.main_layout,null);




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

        mFragmensts = Data.getFragments();

        contentAdapter = new contentAdapter(this, MainActivity.this);
        bottomNavigationView = findViewById(R.id.bottom_navigation_1);

        this.contenRv = findViewById(R.id.content_rv);
        this.contenRv.setAdapter(contentAdapter);
        this.contenRv.setLayoutManager(new LinearLayoutManager(this));
//        LayoutInflater inflate = LayoutInflater.from(MainActivity.this);
//        View view = inflate.inflate(R.layout.main_layout,null);
//        setContentView(view);

        initView();

}
    private void initView(){
        //导航栏的监听器
        if (ClickUtil.isFastClick()) {
            bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
                Log.d(TAG, item.getItemId() + " item was selected-------------------");
                onTabItemSelected(item.getItemId());//调用跳转方法
                return true;
            });
            onTabItemSelected(R.id.page_1);
        }
    }
    //跳转方法
    private void onTabItemSelected(int id){
        Fragment fragment = null;
        switch (id){
            case R.id.page_1:
                fragment = mFragmensts[0];
                break;
            case R.id.page_2:
                fragment = mFragmensts[1];
                break;
            case R.id.page_3:
                break;
        }
        if (fragment != null){
            getSupportFragmentManager().beginTransaction().replace(R.id.home_container,fragment).commit();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        //把主线程的handler传递给子线程使用
        lab.getData(handler);
    }
    @Override
    protected void onDestroy(){
        super.onDestroy();
        //退出清理缓存
        priPreference.saveUser(priPreference.currentUser(UserLab.USER_CURRENT),null);

    }

    @Override
    public void onContentClick(int position) {

    }
}
