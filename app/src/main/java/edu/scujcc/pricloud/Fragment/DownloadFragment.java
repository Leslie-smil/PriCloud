package edu.scujcc.pricloud.Fragment;

import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.io.File;

import edu.scujcc.pricloud.Activities.DownloadActivity;
import edu.scujcc.pricloud.Activities.UploadActivity;
import edu.scujcc.pricloud.R;
import edu.scujcc.pricloud.Utils.ClickUtil;

import static android.content.Context.DOWNLOAD_SERVICE;

public class DownloadFragment extends Fragment {
    private static DownloadFragment downloadFragment = null;
    private View view;
    private Button button;
    private long downloadID;
    private TextView textView1, textView2;
    private BroadcastReceiver onDownloadComplete = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            long id = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1);
            if (downloadID == id) {
                Toast.makeText(getActivity(), "Download Completed", Toast.LENGTH_SHORT).show();
            }
        }
    };

    private DownloadFragment() {
    }

    public static DownloadFragment getInstance() {
        if (downloadFragment == null) {
            downloadFragment = new DownloadFragment();
        }
        return downloadFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_load, container, false);
        button = view.findViewById(R.id.l_download);
        textView1 = view.findViewById(R.id.load_upload);
        textView2 = view.findViewById(R.id.load_overload);

        getContext().registerReceiver(onDownloadComplete, new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE));

        button.setOnClickListener(view -> beginDownload());
        if (ClickUtil.isFastClick()) {
            textView1.setOnClickListener(v -> {
                Intent intent = new Intent(getActivity(), UploadActivity.class);
                startActivity(intent);
            });
        }
        if (ClickUtil.isFastClick()) {
            textView2.setOnClickListener(v -> {
                Intent intent = new Intent(getActivity(), DownloadActivity.class);
                startActivity(intent);
            });
        }
        return view;
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        getActivity().unregisterReceiver(onDownloadComplete);
    }

    private void beginDownload() {
        File file = new File(getContext().getExternalFilesDir(null), "Dummy");
        DownloadManager.Request request = new DownloadManager.Request(Uri.parse("http://speedtest.ftp.otenet.gr/files/test10Mb.db"))
                .setTitle("Dummy MyOSSFile")
                .setDescription("Downloading")
                .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE)
                .setDestinationUri(Uri.fromFile(file))
                .setAllowedOverMetered(true)
                .setAllowedOverRoaming(true);
        DownloadManager downloadManager = (DownloadManager) getContext().getSystemService(DOWNLOAD_SERVICE);
        downloadID = downloadManager.enqueue(request);
    }


}
