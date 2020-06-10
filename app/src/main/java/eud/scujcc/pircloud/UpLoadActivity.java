package eud.scujcc.pircloud;

import android.Manifest;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.alibaba.sdk.android.oss.ClientException;
import com.alibaba.sdk.android.oss.OSS;
import com.alibaba.sdk.android.oss.OSSClient;
import com.alibaba.sdk.android.oss.ServiceException;
import com.alibaba.sdk.android.oss.callback.OSSCompletedCallback;
import com.alibaba.sdk.android.oss.callback.OSSProgressCallback;
import com.alibaba.sdk.android.oss.common.auth.OSSCredentialProvider;
import com.alibaba.sdk.android.oss.common.auth.OSSPlainTextAKSKCredentialProvider;
import com.alibaba.sdk.android.oss.internal.OSSAsyncTask;
import com.alibaba.sdk.android.oss.model.PutObjectRequest;
import com.alibaba.sdk.android.oss.model.PutObjectResult;

import eud.scujcc.pircloud.permission.KbPermission;
import eud.scujcc.pircloud.permission.KbPermissionListener;
import eud.scujcc.pircloud.permission.KbPermissionUtils;

public class UpLoadActivity extends AppCompatActivity {
    private final static String TAG="pricloud";
    public OSS oss;
    private static final int CHOOSE_FILE_CODE = 0;
    private Uri uri;
    public static final int REQUEST_CODE_READ_EXTERNAL_STORAGE = 1;
    public static final int WRITE_EXTERNAL_STORAGE_REQUEST_CODE = 1;
    public Button button;
    PriPreference preference = PriPreference.getInstance();
    Configure configure = preference.getConfigure();
    OSSCredentialProvider credentialProvider;
    Context context;
    FolderLab folderLab = FolderLab.getInstance();
    private Handler handler = new Handler() {
        //按快捷键Ctrl o
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what) {
                case FolderLab.MSG_FILES:
                    Toast.makeText(UpLoadActivity.this, "上传完成", Toast.LENGTH_LONG).show();

                    Toast.makeText(UpLoadActivity.this, msg.obj.toString(), Toast.LENGTH_LONG).show();
                    break;
                case FolderLab.MSG_FAILURE:
                    break;
            }
        }
    };
    public static String getDataColumn(Context context, Uri uri, String selection,
                                       String[] selectionArgs) {

        Cursor cursor = null;
        final String column = "_data";
        final String[] projection = {
                column
        };

        try {
            cursor = context.getContentResolver().query(uri, projection, selection, selectionArgs,
                    null);
            if (cursor != null && cursor.moveToFirst()) {
                final int column_index = cursor.getColumnIndexOrThrow(column);
                return cursor.getString(column_index);
            }
        } finally {
            if (cursor != null)
                cursor.close();
        }
        return null;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);
        context = this;
        preference.setup(getApplicationContext());
        button=findViewById(R.id.button);
        credentialProvider = new OSSPlainTextAKSKCredentialProvider(configure.getAccessKeyId(), configure.getAccessKeySecret());
        Log.d(TAG, "onCreate: " + credentialProvider.toString());

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ActivityCompat.checkSelfPermission(UpLoadActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE)
                        == PackageManager.PERMISSION_DENIED) {

                    // 判断是否需要显示提示信息
                    if (ActivityCompat.shouldShowRequestPermissionRationale(UpLoadActivity.this,
                            Manifest.permission.READ_EXTERNAL_STORAGE)) {

                    } else {
                        ActivityCompat.requestPermissions(UpLoadActivity.this,
                                new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                                REQUEST_CODE_READ_EXTERNAL_STORAGE);
                    }
                } else {
                    chooseFile();
                }
            }


        });
    }

    public void simpleUpload(String url) {

        // 构造上传请求。

        PutObjectRequest put = new PutObjectRequest(configure.getBucketName(), "test", uri.getPath());
        Log.d(TAG, "URL :" + url);
// 文件元信息的设置是可选的。
// ObjectMetadata metadata = new ObjectMetadata();
// metadata.setContentType("application/octet-stream"); // 设置content-type。
// metadata.setContentMD5(BinaryUtil.calculateBase64Md5(uploadFilePath)); // 校验MD5。
// put.setMetadata(metadata);
        oss = new OSSClient(getApplicationContext(), "hkoss.fuyu.site", credentialProvider);

        try {
            PutObjectResult putResult = oss.putObject(put);
            Log.d("PutObject", "UploadSuccess");
            Log.d("ETag", putResult.getETag());
            Log.d("RequestId", putResult.getRequestId());
        } catch (ClientException e) {
            // 本地异常，如网络异常等。
            e.printStackTrace();
        } catch (ServiceException e) {
            // 服务异常。
            Log.e(TAG, "RequestId"+e.getRequestId());
            Log.e(TAG,"ErrorCode"+ e.getErrorCode());
            Log.e(TAG,"HostId"+ e.getHostId());
            Log.e(TAG,"RawMessage"+ e.getRawMessage());
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @androidx.annotation.NonNull String[] permissions, @androidx.annotation.NonNull int[] grantResults) {
        if (requestCode == REQUEST_CODE_READ_EXTERNAL_STORAGE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                chooseFile();
            } else {
                Toast.makeText(this, "You denied the permission.", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void resumeUpload(String url) {
        // 构造上传请求。
        String[] s = url.split("/");

        Configure configure = preference.getConfigure();
        Log.d(TAG, "resumeupload: " + configure.getBucketName());
        oss = new OSSClient(getApplicationContext(), "oss-cn-hongkong.aliyuncs.com", credentialProvider);

        PutObjectRequest put = new PutObjectRequest(configure.getBucketName(), s[s.length - 1], url);

// 异步上传时可以设置进度回调。
        put.setProgressCallback(new OSSProgressCallback<PutObjectRequest>() {
            @Override
            public void onProgress(PutObjectRequest request, long currentSize, long totalSize) {
                Log.d(TAG, "currentSize: " + currentSize + " totalSize: " + totalSize);
            }
        });

        OSSAsyncTask task = oss.asyncPutObject(put, new OSSCompletedCallback<PutObjectRequest, PutObjectResult>() {
            @Override
            public void onSuccess(PutObjectRequest request, PutObjectResult result) {
                Log.d(TAG, "UploadSuccess");
                Log.d(TAG, result.getETag());
                Log.d(TAG, result.getRequestId());
            }

            @Override
            public void onFailure(PutObjectRequest request, ClientException clientExcepion, ServiceException serviceException) {
                // 请求异常。
                if (clientExcepion != null) {
                    // 本地异常，如网络异常等。
                    clientExcepion.printStackTrace();
                }
                if (serviceException != null) {
                    // 服务异常。
                    Log.e(TAG, serviceException.getErrorCode());
                    Log.e(TAG, serviceException.getRequestId());
                    Log.e(TAG, serviceException.getHostId());
                    Log.e(TAG, serviceException.getRawMessage());
                }
            }
        });
// task.cancel(); // 可以取消任务。
        task.waitUntilFinished(); // 等待任务完成。
    }

    private void chooseFile() {

        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("*/*").addCategory(Intent.CATEGORY_OPENABLE);
        try {
            startActivityForResult(Intent.createChooser(intent, "Choose File"), CHOOSE_FILE_CODE);
        } catch (ActivityNotFoundException e) {
            Toast.makeText(this, "木有文件管理器啊-_-!!", Toast.LENGTH_SHORT).show();
        }
    }
    public static String getPath(final Context context, final Uri uri) {

        final boolean isKitKat = Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT;

        // DocumentProvider
        if (isKitKat && DocumentsContract.isDocumentUri(context, uri)) {
            // ExternalStorageProvider
            if (isExternalStorageDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];

                if ("primary".equalsIgnoreCase(type)) {
                    return Environment.getExternalStorageDirectory() + "/" + split[1];
                }

                // TODO handle non-primary volumes
            }
            // DownloadsProvider
            else if (isDownloadsDocument(uri)) {

                final String id = DocumentsContract.getDocumentId(uri);
                final Uri contentUri = ContentUris.withAppendedId(
                        Uri.parse("content://downloads/public_downloads"), Long.valueOf(id));

                return getDataColumn(context, contentUri, null, null);
            }
            // MediaProvider
            else if (isMediaDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];

                Uri contentUri = null;
                if ("image".equals(type)) {
                    contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                } else if ("video".equals(type)) {
                    contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                } else if ("audio".equals(type)) {
                    contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                }

                final String selection = "_id=?";
                final String[] selectionArgs = new String[] {
                        split[1]
                };

                return getDataColumn(context, contentUri, selection, selectionArgs);
            }
        }
        // MediaStore (and general)
        else if ("content".equalsIgnoreCase(uri.getScheme())) {
            return getDataColumn(context, uri, null, null);
        }
        // File
        else if ("file".equalsIgnoreCase(uri.getScheme())) {
            return uri.getPath();
        }

        return null;
    }

    @Override
// 文件选择完之后，自动调用此函数
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == CHOOSE_FILE_CODE) {
                uri = data.getData();
                if (KbPermissionUtils.needRequestPermission()) { //判断是否需要动态申请权限
                    KbPermission.with(this)
                            .requestCode(100)
                            .permission(Manifest.permission.WRITE_EXTERNAL_STORAGE) //需要申请的权限(支持不定长参数)
                            .callBack(new KbPermissionListener() {
                                @Override
                                public void onPermit(int requestCode, String... permission) { //允许权限的回调
                                    new Thread() {
                                        @Override
                                        public void run() {
                                            super.run();
                                            Log.d(TAG, "run: " + uri);
                                            resumeUpload(getPath(getApplicationContext(), uri));//处理具体下载过程

                                            folderLab.refresh(handler);
                                        }
                                    }.start();
                                }

                                @Override
                                public void onCancel(int requestCode, String... permission) { //拒绝权限的回调
                                    KbPermissionUtils.goSetting(context); //跳转至当前app的权限设置界面
                                }
                            })
                            .send();
                } else {
                    new Thread() {
                        @Override
                        public void run() {
                            super.run();
                            resumeUpload(getPath(getApplicationContext(), uri));//处理具体下载过程
                        }
                    }.start();
                    //处理具体下载过程
                }

            }
        } else {
            Log.e(TAG, "onActivityResult() error, resultCode: " + resultCode);
        }

        super.onActivityResult(requestCode, resultCode, data);
    }


    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is ExternalStorageProvider.
     */
    public static boolean isExternalStorageDocument(Uri uri) {
        return "com.android.externalstorage.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is DownloadsProvider.
     */
    public static boolean isDownloadsDocument(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is MediaProvider.
     */
    public static boolean isMediaDocument(Uri uri) {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }
}
