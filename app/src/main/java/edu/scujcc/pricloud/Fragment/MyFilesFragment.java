package edu.scujcc.pricloud.Fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import edu.scujcc.pricloud.Adapter.ContentAdapter;
import edu.scujcc.pricloud.Lab.FolderLab;
import edu.scujcc.pricloud.Model.MyOSSFile;
import edu.scujcc.pricloud.PriPreference;
import edu.scujcc.pricloud.R;
import edu.scujcc.pricloud.Utils.ClickUtil;

import static edu.scujcc.pricloud.Model.MyOSSFile.TAG;


public class MyFilesFragment extends Fragment implements ContentAdapter.ContentClickListener {
    private static MyFilesFragment myFilesFragment = null;
    public View view;
    MyOSSFile myOSSFile;
    String path;
    private ContentAdapter contentAdapter;
    private RecyclerView contenRv;
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

    private MyFilesFragment() {
    }

    public static MyFilesFragment getInstance() {
        if (myFilesFragment == null) {
            myFilesFragment = new MyFilesFragment();
        }
        return myFilesFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_main, container, false);

        contentAdapter = new ContentAdapter(this, view.getContext());
        contenRv = view.findViewById(R.id.content_rv);
        contenRv.setLayoutManager(new LinearLayoutManager(getActivity()));
        contenRv.setAdapter(contentAdapter);

        file_path = view.findViewById(R.id.file_path);
        lab.getData(handler);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    @Override
    public void onContentClick(int position) {
        Log.d(TAG, "onContentClick: " + position);
        if (ClickUtil.isFastClick()) {
            myOSSFile = lab.getFile(position);
            String s = myOSSFile.getType();
            if (s.equals(MyOSSFile.TPYEISUPPER)) {
                List<String> strings = new ArrayList<>();
                strings = Arrays.asList(path.split("/"));
                List<String> finalStrings = strings;
//                Log.d(TAG, "onContentClick: "+strings.stream()
//                        .filter(s1 -> !s1.equals(finalStrings.get(finalStrings.size() - 1)))
//                        .collect(Collectors.toList()));
                strings = strings.stream()
                        .filter(s1 -> !s1.equals(finalStrings.get(finalStrings.size() - 1)))
                        .collect(Collectors.toList());
                StringBuilder s2 = new StringBuilder();
                for (String s1 : strings) {
                    s2.append(s1).append("/");
                }
                Log.d(TAG, "onContentClick: s2 " + s2);
                path = s2.toString();
                file_path.setText(path);
                lab.getSubdirectoryList(handler, path.replace("/", "."));
            }
            if (s.equals(MyOSSFile.TPYEISFILE)) {
                //TODO 显示文件信息
                Log.d("TAG", "onContentClick: ");
            }
            if (s.equals(MyOSSFile.TPYEISFOLDER)) {
                //TODO 获取下一级文件夹
                if (path == null) {
                    path = myOSSFile.getKey() + "/";
                    file_path.setText(path);
                    lab.getSubdirectoryList(handler, path.replace("/", "."));
                } else {
                    path = path + myOSSFile.getKey() + "/";
                    file_path.setText(path);
                    lab.getSubdirectoryList(handler, path.replace("/", "."));
                }
            }
        }
    }


    private void failed() {
        Toast.makeText(getActivity(), "没有Token，不能访问哦", Toast.LENGTH_LONG).show();
        Log.w("PirCloud", "无Token，禁止访问");
    }

    public void toHome() {
        path = "";
        file_path.setText(path);
        lab.getData(handler);
    }
}
