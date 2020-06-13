package edu.scujcc.pricloud.Api;

import com.squareup.moshi.Moshi;

import edu.scujcc.pricloud.Adapter.DateAdapter;
import edu.scujcc.pricloud.AuthInterceptor;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

/**
 * 使用单例模式创建Retrofit对象。
 */
public class RetrofitClient {
    private static Retrofit INSTANCE = null;

    public static Retrofit getInstance() {
        if (INSTANCE == null) {
            Moshi moshi = new Moshi.Builder()
                    .add(new DateAdapter())
                    .build();

            OkHttpClient client = new OkHttpClient.Builder()
                    .addInterceptor(new AuthInterceptor())
                    .build();

            INSTANCE = new Retrofit.Builder()
                    .baseUrl("https://hk.fuyu.site")  //改为自己的阿里云服务器IP
                    .addConverterFactory(MoshiConverterFactory.create(moshi))
                    .callFactory(client)
                    .build();
        }
        return INSTANCE;
    }
}