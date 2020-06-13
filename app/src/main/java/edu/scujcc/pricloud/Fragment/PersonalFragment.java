package edu.scujcc.pricloud.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import edu.scujcc.pricloud.Activities.LoginActivity;
import edu.scujcc.pricloud.Activities.RecycleBinActivity;
import edu.scujcc.pricloud.Activities.SpManagerActivity;
import edu.scujcc.pricloud.Activities.UserInfoActivity;
import edu.scujcc.pricloud.Activities.UserSetActivity;
import edu.scujcc.pricloud.PriPreference;
import edu.scujcc.pricloud.R;

public class PersonalFragment extends Fragment {
    private static PersonalFragment personalFragment = null;

    PriPreference priPreference = PriPreference.getInstance();
    private ImageView head;
    private Button btnManager, logout;
    private LinearLayout btnRecycle;
    private LinearLayout btnSetting;
    private TextView username;
    private View view;

    private PersonalFragment() {
    }

    public static PersonalFragment getInstance() {
        if (personalFragment == null) {
            personalFragment = new PersonalFragment();
        }
        return personalFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_personal, container, false);
        initView();
        return view;
    }

    void initView() {
        head = view.findViewById(R.id.head);
        head.setImageResource(R.drawable.ic_account_circle_black_24dp);
        btnManager = view.findViewById(R.id.btnManager);
        logout = view.findViewById(R.id.logout);
        btnRecycle = view.findViewById(R.id.btnRecycle);
        btnSetting = view.findViewById(R.id.btnSetting);
        username = view.findViewById(R.id.user_name);
        username.setText(priPreference.getConfigure().getUser().getUsername());
        head.setOnClickListener(v -> toPageActivity(UserInfoActivity.class));
        btnManager.setOnClickListener(v -> toPageActivity(SpManagerActivity.class));
        logout.setOnClickListener(v -> toPageActivity(LoginActivity.class));
        btnRecycle.setOnClickListener(v -> toPageActivity(RecycleBinActivity.class));
        btnSetting.setOnClickListener(v -> toPageActivity(UserSetActivity.class));
    }

    private void toPageActivity(Class<?> page) {
        Intent intent = new Intent(getContext(), page);
        startActivity(intent);
    }

}
