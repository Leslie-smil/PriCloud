package edu.scujcc.pricloud.Activitys;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;

import edu.scujcc.pricloud.R;

public class UserInfoActivity  extends Activity  {
      TextView tv_username,tv_phone;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_personal_data);
        
    }

}
