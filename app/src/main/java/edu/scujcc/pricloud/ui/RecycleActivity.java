package edu.scujcc.pricloud.ui;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.Nullable;

import edu.scujcc.pricloud.R;

public class RecycleActivity extends Activity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_recycle_bin);
    }
}
