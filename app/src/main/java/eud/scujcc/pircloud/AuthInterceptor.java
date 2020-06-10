package eud.scujcc.pircloud;

import java.io.IOException;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @author FSMG
 */

public class AuthInterceptor implements Interceptor {
    PriPreference preference = PriPreference.getInstance();

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request originalResult = chain.request();
        Request request = originalResult.newBuilder()
                .addHeader("token", preference.currentToken())
                .build();
        return chain.proceed(request);
    }
}
