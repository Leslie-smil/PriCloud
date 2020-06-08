package eud.scujcc.pircloud;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity implements contentAdapter.ContentClickListener {
    private RecyclerView contenRv;
    private contentAdapter contentAdapter;
    private FolderLab lab = FolderLab.getInstance();
    private PriPreference priPreference = PriPreference.getInstance();

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
        Toast.makeText(MainActivity.this, "Token无效，禁止访问", Toast.LENGTH_LONG).show();
        Log.w("PirCloud", "服务器禁止访问，因为token无效。");
    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        contentAdapter = new contentAdapter(this, MainActivity.this);

        this.contenRv = findViewById(R.id.content_rv);
        this.contenRv.setAdapter(contentAdapter);
        this.contenRv.setLayoutManager(new LinearLayoutManager(this));

//        BottomNavigationView.OnNavigationItemSelectedListener()
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
