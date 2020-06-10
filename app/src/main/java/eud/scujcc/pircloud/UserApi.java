package eud.scujcc.pircloud;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * @author FSMG
 */
public interface UserApi {
    //使用GET来传密码的都是NT
    @POST("/user/login")
    Call<Result<Configure>> login(@Body User user);

    @POST("/user/register")
    Call<Result<User>> register(@Body User user);
    @GET("/file/info")
    Call<Result<String>> spInfo();
}
