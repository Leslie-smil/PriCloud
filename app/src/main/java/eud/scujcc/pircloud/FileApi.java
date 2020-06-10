package eud.scujcc.pircloud;

import java.net.URL;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface FileApi {
    @GET("/file")
    Call<Result<List<File>>> getFileList();
    @GET("/file/{d}")
    Call<Result<List<File>>> getSubdirectoryList(@Path("d") String d);
    @GET("/file/s/{name}")
    Call<Result<List<File>>> searchFilesByName(@Path("name") String name);
    @GET("file/download/{id}")
    Call<Result<URL>> getDownloadUrl(@Path("id") String id);
    @GET("/file/info")
    Call<Result<List<File>>> getBucketInfo();
    @GET("/file/refresh")
    Call<Result<String>> refresh();
}
