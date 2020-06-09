package eud.scujcc.pircloud.http;

public class HttpUtils {

    private void getUserInfo()
    {
       /* try {
            com.lbq.okhttp3.builder.PostBuilder builder = (com.lbq.okhttp3.builder.PostBuilder) com.lbq.okhttp3.OkHttp3.post().url(Param.host_url + "api/MemberPhoneCt/login");
            builder
                    .addParam("username", userName).
                    addParam("password", password);
            Response response = builder.build().execute();
            String body = response.body().string();
            final JSONObject jsonObject = new JSONObject(body);
            int code = jsonObject.optInt("code");

            if(code==1) {

                String appCookie = response.header("Authorization", "noCookie");
            }else
            {

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        ToastUtils.toast(LoginActivity.this,jsonObject.optString("msg","没有返回值"));

                    }
                });
            }

        }catch (Exception  e)
        {
            e.printStackTrace();
        }*/
    }
}
