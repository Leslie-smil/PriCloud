package edu.scujcc.pricloud.Activities;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.Nullable;

import edu.scujcc.pricloud.R;

public class RecycleBinActivity extends Activity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_recycle_bin);
    }
}
