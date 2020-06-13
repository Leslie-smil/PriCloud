package edu.scujcc.pricloud.Activities;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import edu.scujcc.pricloud.Adapter.ContentAdapter;
import edu.scujcc.pricloud.Lab.FolderLab;
import edu.scujcc.pricloud.Model.MyOSSFile;
import edu.scujcc.pricloud.PriPreference;
import edu.scujcc.pricloud.R;

public class MyFilesActivity extends AppCompatActivity implements ContentAdapter.ContentClickListener {
    private final static String TAG = "PriCloud";
    MyOSSFile myOSSFile;
    String path;
    private RecyclerView contenRv;
    private ContentAdapter contentAdapter;
    private TextView file_path;
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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


//        contentAdapter = new ContentAdapter(this, MyFilesActivity.this);
//
//        this.contenRv = findViewById(R.id.content_rv);
//        this.contenRv.setAdapter(contentAdapter);
//        this.contenRv.setLayoutManager(new LinearLayoutManager(this));
//        file_path = findViewById(R.id.file_path);
//        //点击模式
//        ContentAdapter.setSelectMode(EasyAdapter.SelectMode.CLICK);
//        //单选模式
//        ContentAdapter.setSelectMode(EasyAdapter.SelectMode.SINGLE_SELECT);
//        //多选模式
//        ContentAdapter.setSelectMode(EasyAdapter.SelectMode.MULTI_SELECT);
//        initView();
//        lab.getData(handler);
    }

    //导航栏
//    private void initView() {
//        bottomNavigationView = findViewById(R.id.bottom_navigation);
//        bottomNavigationView.bringToFront();
//        //导航栏的监听器
//        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//                Log.d(TAG, item.getItemId() + " item was selected-------------------");
//                onTabItemSelected(item.getItemId());//调用跳转方法
//                return true;
//            }
//        });
//    }

    //跳转方法
//    private void onTabItemSelected(int id) {
//        switch (id) {
//            case R.id.page_1:
//                file_path.setText(path = "");
//                lab.getData(handler);
//                break;
//            case R.id.page_2:
//                Intent intent = new Intent(MyFilesActivity.this, LoadActivity.class);
//                startActivity(intent);
//                break;
//            case R.id.page_3:
//                Intent intent2 = new Intent(MyFilesActivity.this, PersonalActivity.class);
//                startActivity(intent2);
//                break;
//        }
//    }

    @Override
    protected void onResume() {
        super.onResume();
        //把主线程的handler传递给子线程使用
//        lab.refresh(handler);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //退出清理缓存
        // priPreference.saveUser(priPreference.currentUser(UserLab.USER_CURRENT),null);
    }

    @Override
    public void onContentClick(int position) {
//        Log.d(TAG, "onContentClick: " + position);
//        if (ClickUtil.isFastClick()) {
//            myOSSFile = lab.getFile(position);
//            if (myOSSFile.getType().equals(MyOSSFile.TPYEISFILE)) {
//                //TODO 显示文件信息
//                Log.d("TAG", "onContentClick: ");
//            }
//            if (myOSSFile.getType().equals(MyOSSFile.TPYEISFOLDER)) {
//                //TODO 获取下一级文件夹
//                if (path == null) {
//                    path = myOSSFile.getKey() + "/";
//                    file_path.setText(path);
//                    lab.getSubdirectoryList(handler, path.replace("/", "."));
//                } else {
//                    path = path + myOSSFile.getKey() + "/";
//                    file_path.setText(path);
//                    lab.getSubdirectoryList(handler, path.replace("/", "."));
//                }
//            }
//        }
    }

    private void failed() {
        Toast.makeText(MyFilesActivity.this, "没有Token，不能访问哦", Toast.LENGTH_LONG).show();
        Log.w("PirCloud", "无Token，禁止访问");
    }
}
