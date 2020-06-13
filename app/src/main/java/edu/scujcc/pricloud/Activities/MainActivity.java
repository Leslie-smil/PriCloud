package edu.scujcc.pricloud.Activities;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

import edu.scujcc.pricloud.Adapter.ViewPagerAdapter;
import edu.scujcc.pricloud.Fragment.DownloadFragment;
import edu.scujcc.pricloud.Fragment.MyFilesFragment;
import edu.scujcc.pricloud.Fragment.PersonalFragment;
import edu.scujcc.pricloud.Lab.FolderLab;
import edu.scujcc.pricloud.Model.MyOSSFile;
import edu.scujcc.pricloud.R;

public class MainActivity extends AppCompatActivity {
    private MyFilesFragment myFilesFragment;
    private DownloadFragment downloadFragment;
    private PersonalFragment personalFragment;
    private List<Fragment> fragmentList;
    private FragmentManager fragmentManager;
    private ViewPager viewPager;
    private ViewPagerAdapter viewPagerAdapter;
    private BottomNavigationView bottomNavigationView;
    private FolderLab folderLab = FolderLab.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        myFilesFragment = MyFilesFragment.getInstance();
        downloadFragment = DownloadFragment.getInstance();
        personalFragment = PersonalFragment.getInstance();

        fragmentList = new ArrayList<>();
        fragmentList.add(myFilesFragment);
        fragmentList.add(downloadFragment);
        fragmentList.add(personalFragment);

        viewPager = findViewById(R.id.main_viewpager);

        fragmentManager = getSupportFragmentManager();
        viewPagerAdapter = new ViewPagerAdapter(fragmentManager, fragmentList);

        viewPager.setAdapter(viewPagerAdapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                bottomNavigationView.getMenu().getItem(position).setChecked(true);
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        initView();
    }

    public void initView() {
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.bringToFront();
        bottomNavigationView.getMenu().getItem(0).setChecked(false);
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            Log.d(MyOSSFile.TAG, item.getItemId() + " item was selected-------------------");
            onTabItemSelected(item.getItemId());//调用跳转方法
            return false;
        });
    }

    private void onTabItemSelected(int itemId) {
        switch (itemId) {
            case R.id.page_1:
                myFilesFragment.toHome();
                viewPager.setCurrentItem(0);
                break;
            case R.id.page_2:
                viewPager.setCurrentItem(1);
                break;
            case R.id.page_3:
                viewPager.setCurrentItem(2);
                break;
        }
    }
}
