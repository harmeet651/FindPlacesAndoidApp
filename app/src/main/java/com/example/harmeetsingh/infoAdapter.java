package com.example.harmeetsingh;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class infoAdapter extends FragmentStatePagerAdapter{

    int noOfTabs;

    public infoAdapter(FragmentManager fm, int noOfTabs) {
        super(fm);
        this.noOfTabs = noOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                infoTab tab1 = new infoTab();
                return tab1;
            case 1:
                photoTab tab2 = new photoTab();
                return tab2;
            case 2:
                mapTab tab3 = new mapTab();
                return tab3;
            case 3:
                ReviewsFragment tab4 = new ReviewsFragment();
                return tab4;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return noOfTabs;
    }
}
