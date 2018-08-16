package com.example.harmeetsingh;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class PagerAdapter extends FragmentStatePagerAdapter{

    int noOfTabs;

    public PagerAdapter(FragmentManager fm, int NoOfTabs)
    {
        super(fm);
        this.noOfTabs=NoOfTabs;
    }

    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                FrontTab tab1 = new FrontTab();
                return tab1;
            case 1:
                FrontTab2 tab2 = new FrontTab2();
                return tab2;
            default:
                return null;
        }
    }
    @Override
    public int getCount() {
        return 2;
    }
}
