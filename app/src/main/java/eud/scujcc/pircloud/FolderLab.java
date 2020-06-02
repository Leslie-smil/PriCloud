package eud.scujcc.pircloud;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class FolderLab {
    //单例第1步
    private static FolderLab INSTANCE = null;

    private List<File> data;

    public final static int MSG_FILES = 1;
    public final static int MSG_FAILURE = 4;
    private final static String TAG = "PirCloud";

    //单例第2步
    private FolderLab() {
        //初始化空白列表
        data = new ArrayList<>();
        //删除网络访问
        //getData();
    }

    //单例第3步
    public static FolderLab getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new FolderLab();
        }
        return INSTANCE;
    }

    public int getSize(){
        return data.size();
    }

    public File getFile(int positon){
        return this.data.get(positon);
    }

    public void getData(Handler handler) {
        //调用单例
        Retrofit retrofit = RetrofitClient.getInstance();
        FileApi api = retrofit.create(FileApi.class);
        Call<Result<List<File>>> call = api.getAllFiles();
        //enqueue会自己生成子线程， 去执行后续代码
        call.enqueue(new Callback<Result<List<File>>>() {
            @Override
            public void onResponse(Call<Result<List<File>>> call,
                                   Response<Result<List<File>>> response) {
                if (response.code() == 403) {  //缺少token或token错误
                    Message msg = new Message();
                    msg.what = MSG_FAILURE;
                    handler.sendMessage(msg);
                } else if (null != response && null != response.body()) {
                    Log.d(TAG, "从服务器得到的数据是：");
                    Log.d(TAG, response.body().toString());
                    Result<List<File>> result = response.body();
                    data = result.getData();
                    //发出通知
                    Message msg = new Message();
                    msg.what = MSG_FILES;
                    handler.sendMessage(msg);
                } else {
                    Log.w(TAG, "response没有数据！");
                }
            }

            @Override
            public void onFailure(Call<Result<List<File>>> call, Throwable t) {
                Log.e(TAG, "访问网络失败！", t);

            }
        });
    }

}
