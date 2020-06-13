package edu.scujcc.pricloud.Activities;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.Nullable;

import edu.scujcc.pricloud.Lab.UserLab;
import edu.scujcc.pricloud.Model.Result;
import edu.scujcc.pricloud.R;
import edu.scujcc.pricloud.Utils.StringUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SpManagerActivity extends Activity {
    TextView total, used, surplus;
    String[] dw = new String[]{"kb", "M", "G"};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_capacity);
        used = findViewById(R.id.used);
        surplus = findViewById(R.id.surplus);
        UserLab.getInstance().getSpInfo(new Callback<Result<String>>() {
            @Override
            public void onResponse(Call<Result<String>> call, Response<Result<String>> response) {

                Log.e("====test", "result:" + "======");

                Result<String> result = response.body();
                if (result != null) {
                    Log.e("====test", "result:" + result.toString());

                    if (result.getStatus() == 1) {
                        long userSize = Long.parseLong(result.getData());
                        //  dwSpaces(userSize,0);
                        float dwSize;
                        String showUseSize;

                        dwSize = userSize / 1024;
                        if (dwSize < 1024) {
                            showUseSize = StringUtils.formatDouble4(dwSize) + "KB";
                        } else {
                            dwSize = dwSize / 1024;
                            if (dwSize < 1024) {
                                showUseSize = StringUtils.formatDouble4(dwSize) + "M";
                            } else {
                                dwSize = dwSize / 1024;
                                showUseSize = StringUtils.formatDouble4(dwSize) + "G";
                            }
                        }

                        used.setText(showUseSize);
                        float spSize = (float) ((40 * Math.pow(1024d, 3d) - userSize) / Math.pow(1024d, 3d));
                        surplus.setText(spSize + "G");
                    }
                }

            }

            @Override
            public void onFailure(Call<Result<String>> call, Throwable t) {
                Log.e("====test", "onFailure:" + "======");

            }
        });
    }

    /**
     * 递归计算合适的空间单位
     *
     * @param size
     * @param point
     */
    private void dwSpaces(double size, int point) {
        size = size / 1024;
        if (size > 1024 && point < dw.length) {
            dwSpaces(size, point++);
        } else {
            used.setText(StringUtils.formatDouble4(size) + dw[point]);

        }
    }
}
