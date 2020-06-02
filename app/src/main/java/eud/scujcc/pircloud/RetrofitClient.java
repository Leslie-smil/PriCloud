package eud.scujcc.pircloud;

import com.squareup.moshi.Moshi;

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
                    .build();
            OkHttpClient client = new OkHttpClient.Builder()
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