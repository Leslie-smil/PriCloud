package eud.scujcc.pircloud;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import eud.scujcc.pircloud.ui.RecycleActivity;
import eud.scujcc.pircloud.ui.SetingActivity;
import eud.scujcc.pircloud.ui.SpManagerActivity;
import eud.scujcc.pircloud.ui.UserInfoActivity;

public class PersonalActivity extends Activity implements View.OnClickListener {

    Configure  mConfigure;

    ImageView head;
    Button btnManager,logout;
    ConstraintLayout btnRecycle;
    ConstraintLayout btnSetting;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_personal);
        mConfigure=PriPreference.getInstance().getConfigure();

        if(mConfigure!=null)
        {

        }
    }

    void  initView()
    {
        head=findViewById(R.id.head);
        btnManager=findViewById(R.id.btnManager);
        logout=findViewById(R.id.logout);
        btnRecycle=findViewById(R.id.btnRecycle);
        btnSetting=findViewById(R.id.btnSetting);

    }

    @Override
    public void onClick(View v) {
        int _id=v.getId();

        switch (_id)
        {
            case  R.id.head:
                toPageActivity(UserInfoActivity.class);
                break;
            case  R.id.btnManager:
                toPageActivity(SpManagerActivity.class);
                break;
            case  R.id.logout:break;
            case  R.id.btnRecycle:
                toPageActivity(RecycleActivity.class);
                break;
            case  R.id.btnSetting:
                toPageActivity(SetingActivity.class);

                break;

        }

    }

    private  void  toPageActivity(Class<?> page)
    {

        Intent intent=new Intent(this,page);

        startActivity(intent);
    }
}
