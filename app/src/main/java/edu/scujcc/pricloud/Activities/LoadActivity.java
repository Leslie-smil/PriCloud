package edu.scujcc.pricloud.Activities;

import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.File;

import edu.scujcc.pricloud.R;
import edu.scujcc.pricloud.Utils.ClickUtil;

public class LoadActivity extends AppCompatActivity {
    private final static String TAG="pricloud";
    private Button button;
    private long downloadID;
    private TextView textView1,textView2;
    private BottomNavigationView bottomNavigationView;

    private BroadcastReceiver onDownloadComplete = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            long id = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1);
            if (downloadID == id) {
                Toast.makeText(LoadActivity.this, "Download Completed", Toast.LENGTH_SHORT).show();
            }
        }
    };
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load);
        button=findViewById(R.id.l_download);
        textView1=(TextView) findViewById(R.id.load_upload);
        textView2=findViewById(R.id.load_overload);
        initView();//调用导航栏监听器
        registerReceiver(onDownloadComplete,new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE));

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                beginDownload();
            }
        });
        if (ClickUtil.isFastClick()) {
            textView1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(LoadActivity.this, UpLoadActivity.class);
                    startActivity(intent);
                }
            });
        }
        if (ClickUtil.isFastClick()) {
            textView2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(LoadActivity.this, OverLoadActivity.class);
                    startActivity(intent);
                }
            });
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unregisterReceiver(onDownloadComplete);
    }

    private void beginDownload(){
        File file=new File(getExternalFilesDir(null),"Dummy");
        DownloadManager.Request request=new DownloadManager.Request(Uri.parse("http://speedtest.ftp.otenet.gr/files/test10Mb.db"))
                .setTitle("Dummy MyOSSFile")
                .setDescription("Downloading")
                .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE)
                .setDestinationUri(Uri.fromFile(file))
                .setAllowedOverMetered(true)
                .setShowRunningNotification(true)
                .setAllowedOverRoaming(true);
        DownloadManager downloadManager= (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
        downloadID = downloadManager.enqueue(request);
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
        bottomNavigationView.getMenu().getItem(1).setChecked(true);//设置默认选中item
    }
    //跳转方法
    private void onTabItemSelected(int id) {
        switch (id) {
            case R.id.page_1:
                Intent intent = new Intent(LoadActivity.this, MainActivity.class);
                startActivity(intent);
                break;
            case R.id.page_2:
                break;
            case R.id.page_3:
                Intent intent1 = new Intent(LoadActivity.this,PersonalActivity.class);
                startActivity(intent1);
                break;
        }
    }
}
