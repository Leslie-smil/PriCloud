package eud.scujcc.pircloud.ui;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;

import eud.scujcc.pircloud.R;
import eud.scujcc.pircloud.Result;
import eud.scujcc.pircloud.UserLab;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SpManagerActivity  extends Activity {
     TextView total,used,surplus;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_capacity);

        UserLab.getInstance().getSpInfo(new Callback<Result<String>>() {
            @Override
            public void onResponse(Call<Result<String>> call, Response<Result<String>> response) {

                Log.e("====test","result:"+"======");

                Result<String> result = response.body();
                if(result!=null)
                {
                    Log.e("====test","result:"+result.toString());

                    if(result.getStatus()==1)
                    {
                        long  userSize=Long.parseLong(result.getData());
                    }
                }

            }

            @Override
            public void onFailure(Call<Result<String>> call, Throwable t) {
                Log.e("====test","onFailure:"+"======");

            }
        });
    }
}
