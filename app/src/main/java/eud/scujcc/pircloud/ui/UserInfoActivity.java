package eud.scujcc.pircloud.ui;

import android.app.Activity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;


import androidx.annotation.Nullable;

import eud.scujcc.pircloud.R;

public class UserInfoActivity  extends Activity  {
      TextView tv_username,tv_phone;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_personal_data);
        
    }

}
