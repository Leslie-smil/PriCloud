package edu.scujcc.pricloud.Api;

import java.net.URL;
import java.util.List;

import edu.scujcc.pricloud.Model.MyOSSFile;
import edu.scujcc.pricloud.Model.Result;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface FileApi {
    @GET("/file")
    Call<Result<List<MyOSSFile>>> getFileList();
    @GET("/file/{d}")
    Call<Result<List<MyOSSFile>>> getSubdirectoryList(@Path("d") String d);
    @GET("/file/s/{name}")
    Call<Result<List<MyOSSFile>>> searchFilesByName(@Path("name") String name);
    @GET("file/download/{id}")
    Call<Result<URL>> getDownloadUrl(@Path("id") String id);
    @GET("/file/info")
    Call<Result<List<MyOSSFile>>> getBucketInfo();
    @GET("/file/refresh")
    Call<Result<String>> refresh();
}
