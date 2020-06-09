package eud.scujcc.pircloud;

import androidx.fragment.app.Fragment;

public class Data {

    public static Fragment[] getFragments(){
        Fragment fragments[] = new Fragment[3];
        fragments[0] = new HomeFragment();
        fragments[1] = new UpFragment();
        return fragments;
    }


}
